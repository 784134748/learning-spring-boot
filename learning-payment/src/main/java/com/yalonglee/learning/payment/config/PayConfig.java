package com.yalonglee.learning.payment.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
class PayConfig {

    private final String HTTP = "http";
    private final String HTTPS = "https";
    private final int MAX_URL_LENGTH = 256;

    /**
     * 支付完成后的异步通知地址.
     */
    private String notifyUrl;

    /**
     * 支付完成后的同步返回地址.
     */
    private String returnUrl;

    public void check() {
        Objects.requireNonNull(notifyUrl, "config param 'notifyUrl' is null.");
        if (!notifyUrl.startsWith(HTTP) && !notifyUrl.startsWith(HTTPS)) {
            throw new IllegalArgumentException("config param 'notifyUrl' does not start with http/https.");
        }
        if (notifyUrl.length() > MAX_URL_LENGTH) {
            throw new IllegalArgumentException("config param 'notifyUrl' is incorrect: size exceeds 256.");
        }
        if (returnUrl != null) {
            if (!returnUrl.startsWith(HTTP) && !returnUrl.startsWith(HTTPS)) {
                throw new IllegalArgumentException("config param 'returnUrl' does not start with http/https.");
            }
            if (returnUrl.length() > MAX_URL_LENGTH) {
                throw new IllegalArgumentException("config param 'returnUrl' is incorrect: size exceeds 256.");
            }
        }
    }


}
