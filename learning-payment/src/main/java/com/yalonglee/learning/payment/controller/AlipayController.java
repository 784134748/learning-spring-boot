package com.yalonglee.learning.payment.controller;

import com.yalonglee.learning.payment.domain.AlipayTradAppPayBO;
import com.yalonglee.learning.payment.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/aliPay")
public class AlipayController {

    @Autowired
    private AlipayService aliPayService;

    /**
     * 获取支付宝加签的订单信息字符串
     */
    @GetMapping("AliPayAppPay")
    public void AlipayAppPay(AlipayTradAppPayBO alipayTradAppPay) {
        aliPayService.constructPaymentRequest(alipayTradAppPay);
    }

    /**
     * 支付宝支付成功后，异步请求该接口
     */


}
