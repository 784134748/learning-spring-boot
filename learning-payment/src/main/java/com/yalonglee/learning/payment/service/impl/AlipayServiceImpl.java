package com.yalonglee.learning.payment.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.yalonglee.learning.payment.config.AlipayConfig;
import com.yalonglee.learning.payment.domain.AlipaySecondCheck;
import com.yalonglee.learning.payment.domain.AlipayTradAppPayBO;
import com.yalonglee.learning.payment.service.AlipayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
@Slf4j
public class AlipayServiceImpl implements AlipayService {

    @Autowired
    private AlipayConfig alipayConfig;

    @Override
    public String constructPaymentRequest(AlipayTradAppPayBO aliPayTradAppPay) {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getServiceUrl(), alipayConfig.getAppId(), alipayConfig.getAppRSAPrivateKey(), alipayConfig.getFormat(), alipayConfig.getCharset(), alipayConfig.getAliPayRSAPublicKey(), alipayConfig.getSignType());

        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest AppPayRequest = new AlipayTradeAppPayRequest();

        //SDK已经封装掉了公共参数，这里只需要传入业务参数。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();

        //业务参数传入,可以传很多，参考API
        model.setPassbackParams(aliPayTradAppPay.getPassbackParams());
        model.setBody(aliPayTradAppPay.getBody());
        model.setSubject(aliPayTradAppPay.getSubject());
        model.setOutTradeNo(aliPayTradAppPay.getOutTradeNo());
        model.setTimeoutExpress(aliPayTradAppPay.getTimeoutExpress());
        model.setTotalAmount(aliPayTradAppPay.getTotalAmount());
        model.setProductCode(aliPayTradAppPay.getProductCode());
        AppPayRequest.setBizModel(model);

        log.info("异步通知的地址为:{}", alipayConfig.getNotifyUrl());
        AppPayRequest.setNotifyUrl(alipayConfig.getNotifyUrl());
        AppPayRequest.setReturnUrl(alipayConfig.getReturnUrl());

        //这里和普通的接口调用不同，使用的是sdkExecute
        //返回支付宝订单信息(预处理)
        AlipayTradeAppPayResponse alipayTradeAppPayResponse = null;
        try {
            alipayTradeAppPayResponse = alipayClient.sdkExecute(AppPayRequest);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        //就是orderString 可以直接给APP请求，无需再做处理。
        String orderString = alipayTradeAppPayResponse.getBody();
        return orderString;
    }

    @Override
    public String notifyResult(HttpServletRequest request, HttpServletResponse response) {
        log.info("支付宝异步返回支付结果");
        //从支付宝回调的request域中取值
        Map<String, String[]> requestParameterMap = request.getParameterMap();
        log.info("返回参数集合：{}", requestParameterMap);
        //处理返回结果
        String status = this.handleReturn(requestParameterMap);
        return status;
    }

    @Override
    public void showOrder() {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getServiceUrl(), alipayConfig.getAppId(), alipayConfig.getAppRSAPrivateKey(), alipayConfig.getFormat(), alipayConfig.getCharset(), alipayConfig.getAliPayRSAPublicKey(), alipayConfig.getSignType());
        //创建API对应的request类
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        //设置业务参数
        request.setBizContent("{" +
                " \"out_trade_no\":\"20150320010101001\"," +
                " \"trade_no\":\"2014112611001004680073956707\"" +
                " }");
        //通过alipayClient调用API，获得对应的response类
        AlipayTradeQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
            if (response.isSuccess()) {
                AlipaySecondCheck alipaySecondCheck = new AlipaySecondCheck();
                BeanUtils.copyProperties(response, alipaySecondCheck);
                check(alipaySecondCheck);
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        System.out.print(response.getBody());
    }

    /**
     * 对返回数据进行处理
     *
     * @return
     */
    public String handleReturn(Map<String, String[]> requestParameterMap) {
        //将异步通知中收到的所有参数都存放到map中
        Map<String, String> paramsMap = new HashMap<>();
        for (Iterator<String> iter = requestParameterMap.keySet().iterator(); iter.hasNext(); ) {
            String key = iter.next();
            String[] values = requestParameterMap.get(key);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            paramsMap.put(key, valueStr);
        }
        //异步通知的校验
        String status = signVerified(paramsMap);
        return status;
    }

    /**
     * 异步通知的验签
     * <p>
     * 其验签步骤为：
     * 第一步： 在通知返回参数列表中，除去sign、sign_type两个参数外，凡是通知返回回来的参数皆是待验签的参数。
     * 第二步： 将剩下参数进行url_decode, 然后进行字典排序，组成字符串，得到待签名字符串：
     * 第三步： 将签名参数（sign）使用base64解码为字节码串。
     * 第四步： 使用RSA的验签方法，通过签名字符串、签名参数（经过base64解码）及支付宝公钥验证签名。
     * 第五步：在步骤四验证签名正确后，必须再严格按照如下描述校验通知数据的正确性
     */
    private String signVerified(Map<String, String> paramsMap) {
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(paramsMap, alipayConfig.getAliPayRSAPublicKey(), alipayConfig.getCharset(), alipayConfig.getSignType());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        if (signVerified) {
            //验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
            AlipaySecondCheck alipaySecondCheck = new AlipaySecondCheck();
            BeanUtils.copyProperties(paramsMap, alipaySecondCheck);
            check(alipaySecondCheck);
            return "success";
        } else {
            //验签失败则记录异常日志，并在response中返回failure.
            return "failure";
        }
    }

    /**
     * 校验通知数据的正确性(二次校验,与数据库中数据进行对比)
     * <p>
     * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号；
     * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）；
     * 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）；
     * 4、验证app_id是否为该商户本身
     * <p>
     * 上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
     * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
     * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
     */
    private boolean check(AlipaySecondCheck alipaySecondCheck) {
        //从数据库取出数据

        //二次校验

        //写入数据库
        writeIntoDB(alipaySecondCheck);
        return true;
    }

    /**
     * 将结果写入数据库
     *
     * @param alipaySecondCheck
     */
    private void writeIntoDB(AlipaySecondCheck alipaySecondCheck) {

    }


}
