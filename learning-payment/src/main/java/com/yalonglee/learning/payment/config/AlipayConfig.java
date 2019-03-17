package com.yalonglee.learning.payment.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 支付宝配置信息(非即时到账接口)
 * <p>
 * 文档地址: https://openhome.alipay.com/developmentDocument.htm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
public class AlipayConfig {

    /**
     * 应用id, 支付宝新版App支付, Wap支付的统一ID.
     */
    @Value("2016092300575819")
    private String appId;

    /**
     * 应用的RSA私钥(合作伙伴自行创建).
     */
    @Value("MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCGxIKS3oWKso/vpnR+oI3ariqejc1PLLsnjgkVK1XHHkJ5+9c7wBeutIyZEFaH6+bEnJvhcsnOUfL0YjvLNI+KPjU26c4mlx15EDdDNwPTP0XbeDp7EC9OD9ADumOW/VpEI2l40Oj3tj6hAGPAJOZIvEOf1zf2CwEFblXY4ky+Y9j53S74962Bu6FG4HrTh5oJEoWidaZW5s2eH320xrozV9PJjGCbShc7logiyG+U000tBsTO0Nh8aEKXOAW6Dzvv1PYuGtuhkhATXzQURBdCYrx2l0r2w1Gxxf4aMLJS7+XgrSSvj4IPs/h08ESmi3xHA2qitoByOeVTxZNYRp+lAgMBAAECggEAXWRAuFbMZFlptcG8aSQKKckYb9gEtyFgcQDU+n6Vau9lUfNcLGPqBX/JDylrTbYErat5uro5b2kK3KBWn3NFJaAGcI+F9Ogz3HmIUmQFmpb2aijfHfkRTx9WH79C1JetvhpsNPVQ/JimvhvvBHCNxUnx8wrKFQ97HbBgfXQVirnTr8wKEB2i7hOHDgMPlBF6xFmOmftNEg0DAZa5WNGdPo0kkNxUNARcpQ33N+sMAijWNjIFPtmwhIUdD0JPILksKvbAzkUaD3v88xaO3kSuwWGUuBqJt9xLCiOCScSMN/qFU+AnlpGekwAARtHT2eGeJgUtpcmv7aS2HL5oM3FTAQKBgQDQkoSrtlIF85/4TpQXjbVnHKLIui5/LDqGWJ/lDzs0SoXChBBjo3/OzY12Ok5jDttaSoE+OYOgdfe7oXHzvtbqH4enbS2KJduUwOGbf+P9MLeuUHrxovHZ5NmHmZ1e/APiy7JxtrMgI9yMbiliFbOarO6fJQSFQILEBC0JyI9GiQKBgQClaaWoky/lctWwoJQC0jGI4qv3mZ9z7ph/pay4FHXMb5gDe9zph8XcJovdP/3tbI2dTSsMGjNLEKTq9FWRt1n6N6Q6Y0cwcsuPkyLPbpyLffAD256Glqi7RTK9Z97THHEZruZuqVpYyUMiNr7MUDzNT79GiVVTT+b/wKqutWgJPQKBgDD3VQJjKVqQWawX9piZAk+U33q0ixn8LjlAMTm7m7CpFkIWcyqRRf4tL9rj0WdcI49NEo6jPg275E8+ldwdn6MTh+t8rEZUoPqJVe4F1maou1bHOjXkhVccbR1yqQmVrOvf2qLru53+DVdCvaBza9kSZGska61E+e4+9LvpRAnRAoGASeTZQWaHZgwX1DDYDi6SPGFui0zQg7zZ2WRMtWtGeI59rUi/FoA17rQ1lSBKMq9k0BmyGlT2BxmnYuLF+zOaeYIz1nWVTtppf3kuetYiDtqyxzZsKhnRxN8T3LMzQsIY8G1GubbkCnfWislEEQfjMaDnhP5C5NX0DtqmJSgbEs0CgYBd5+cMU7FIw8km1VVBVZoBxi2ygm7GuUlCvhTY+M4YkKjh4+TfJ2k3LWySQ75GOgkOY2OF7fe4PWBwMxTEQCLe/bbfNXKPFiDICxIMSV2VoM6grpx+UQ/AuSC7isDHtpn+sHF+IQq3Fw1cq6nT94NPu65GyhXAFGSi29u+4AKKCA==")
    private String appRSAPrivateKey;

    /**
     * 支付宝的RSA公钥(由合作伙伴上传RSA公钥后支付宝提供).
     */
    @Value("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhsSCkt6FirKP76Z0fqCN2q4qno3NTyy7J44JFStVxx5CefvXO8AXrrSMmRBWh+vmxJyb4XLJzlHy9GI7yzSPij41NunOJpcdeRA3QzcD0z9F23g6exAvTg/QA7pjlv1aRCNpeNDo97Y+oQBjwCTmSLxDn9c39gsBBW5V2OJMvmPY+d0u+PetgbuhRuB604eaCRKFonWmVubNnh99tMa6M1fTyYxgm0oXO5aIIshvlNNNLQbEztDYfGhClzgFug8779T2LhrboZIQE180FEQXQmK8dpdK9sNRscX+GjCyUu/l4K0kr4+CD7P4dPBEpot8RwNqoraAcjnlU8WTWEafpQIDAQAB")
    private String aliPayRSAPublicKey;

    /**
     * 支付完成后的异步通知地址.
     */
    @Value("http://www.xxx.com/alipay/notify_url.do")
    private String notifyUrl;

    /**
     * 支付完成后的同步返回地址.
     */
    @Value("http://www.xxx.com/alipay/return_url.do")
    private String returnUrl;

    /**
     * 支付宝网关
     */
    @Value("https://openapi.alipay.com/gateway.do")
    private String serviceUrl;

    /**
     * 编码
     */
    @Value("utf-8")
    private String charset;

    /**
     * 返回格式（当前只支持json）
     */
    @Value("json")
    private String format;

    /**
     * 签名方式: RSA, RSA2两个值可选, 必须大写.
     */
    @Value("RSA2")
    private String signType;

}
