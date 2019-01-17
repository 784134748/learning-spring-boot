package com.yalonglee.learning.payment.config;

/**
 * 签名方式.
 * <p>
 * 即时到账(老接口)支持MD5和RSA的签名方式, App支付和Wap支付支持RSA和RSA2的签名方式. DSA签名方式暂时不支持.
 */
public enum SignType {
    MD5, RSA, RSA2
}
