package com.yalonglee.learning.payment.service;

import com.yalonglee.learning.payment.domain.AlipayTradAppPayBO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AlipayService {

    /**
     * 构造支付请求,发送支付请求,展示支付信息
     */
    String constructPaymentRequest(AlipayTradAppPayBO aliPayTradAppPay);

    /**
     * 后台通知支付结果
     */
    String notifyResult(HttpServletRequest request, HttpServletResponse response);

    /**
     * 展示订单信息
     */
    void showOrder();

}
