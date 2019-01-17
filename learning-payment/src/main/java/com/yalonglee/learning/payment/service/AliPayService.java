package com.yalonglee.learning.payment.service;

import com.yalonglee.learning.payment.bo.AliPayTradAppPayBO;

public interface AliPayService {

    /**
     * 构造支付请求
     */
    String constructPaymentRequest(AliPayTradAppPayBO aliPayTradAppPay);

    /**
     * 后台通知支付结果
     */
    String notifyResult();

    /**
     * 对返回数据进行处理
     */
    Byte handleReturn();

    /**
     * 展示订单信息
     */
    void showOrder();





}
