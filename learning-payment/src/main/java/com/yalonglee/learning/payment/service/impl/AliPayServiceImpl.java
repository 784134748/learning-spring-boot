package com.yalonglee.learning.payment.service.impl;

import com.yalonglee.learning.payment.bo.AliPayTradAppPayBO;
import com.yalonglee.learning.payment.service.AliPayService;

public class AliPayServiceImpl implements AliPayService {

    @Override
    public String constructPaymentRequest(AliPayTradAppPayBO aliPayTradAppPay) {
        return null;
    }

    @Override
    public String notifyResult() {
        return null;
    }

    @Override
    public Byte handleReturn() {
        return null;
    }

    @Override
    public void showOrder() {

    }

    /**
     * 异步通知的验签
     *
     * 其验签步骤为：
     * 第一步： 在通知返回参数列表中，除去sign、sign_type两个参数外，凡是通知返回回来的参数皆是待验签的参数。
     * 第二步： 将剩下参数进行url_decode, 然后进行字典排序，组成字符串，得到待签名字符串：
     * 第三步： 将签名参数（sign）使用base64解码为字节码串。
     * 第四步： 使用RSA的验签方法，通过签名字符串、签名参数（经过base64解码）及支付宝公钥验证签名。
     * 第五步：在步骤四验证签名正确后，必须再严格按照如下描述校验通知数据的正确性
     */
    private void checkNotifyResult() {

        //校验通知数据的正确性
        checkResult();
    }

    /**
     * 校验通知数据的正确性
     *
     * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号；
     * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）；
     * 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）；
     * 4、验证app_id是否为该商户本身
     *
     * 上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
     * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
     * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
     */
    private void checkResult() {

    }
}
