package com.yalonglee.learning.payment.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.binary.Base64;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

/**
 * 支付宝配置信息(非即时到账接口)
 * <p>
 * 文档地址: https://openhome.alipay.com/developmentDocument.htm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AliPayConfig extends PayConfig {

    private final String CHARSET = "UTF-8";
    private final int MAX_APP_ID_LENGTH = 32;

    /**
     * 应用id, 支付宝新版App支付, Wap支付的统一ID.
     */
    private String appId;

    private String appRSAPrivateKey;

    private String aliPayRSAPublicKey;

    /**
     * 应用的RSA私钥(合作伙伴自行创建).
     */
    private PrivateKey appRSAPrivateKeyObject;

    /**
     * 支付宝的RSA公钥(由合作伙伴上传RSA公钥后支付宝提供).
     */
    private PublicKey aliPayRSAPublicKeyObject;

    /**
     * 签名方式: RSA, RSA2两个值可选, 必须大写.
     */
    private SignType signType;

    @Builder
    public AliPayConfig(String notifyUrl, String returnUrl, String appId, String appRSAPrivateKey, String aliPayRSAPublicKey, PrivateKey appRSAPrivateKeyObject, PublicKey aliPayRSAPublicKeyObject, SignType signType) {
        super(notifyUrl, returnUrl);
        this.appId = appId;
        this.appRSAPrivateKey = appRSAPrivateKey;
        this.aliPayRSAPublicKey = aliPayRSAPublicKey;
        this.appRSAPrivateKeyObject = appRSAPrivateKeyObject;
        this.aliPayRSAPublicKeyObject = aliPayRSAPublicKeyObject;
        this.signType = signType;
    }

    @Override
    public void check() {
        super.check();

        Objects.requireNonNull(appId, "config param 'appId' is null.");
        if (appId.length() > MAX_APP_ID_LENGTH) {
            throw new IllegalArgumentException("config param 'appId' is incorrect: size exceeds 32.");
        }

        Objects.requireNonNull(signType, "config param 'signType' is null.");
        switch (signType) {
            case MD5:
                throw new IllegalArgumentException("config param 'signType' [" + signType + "] is not match.");
            case RSA:
            case RSA2:
                Objects.requireNonNull(appRSAPrivateKey, "config param 'appRSAPrivateKey' is null.");
                try {
                    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                    this.appRSAPrivateKeyObject = keyFactory.generatePrivate(
                            new PKCS8EncodedKeySpec(Base64.decodeBase64(appRSAPrivateKey)));
                } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                    throw new IllegalArgumentException("config param 'appRSAPrivateKey' is incorrect.", e);
                }
                Objects.requireNonNull(aliPayRSAPublicKey, "config param 'aliPayRSAPublicKey' is null.");
                try {
                    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                    this.aliPayRSAPublicKeyObject = keyFactory.generatePublic(
                            new X509EncodedKeySpec(Base64.decodeBase64(aliPayRSAPublicKey)));
                } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                    throw new IllegalArgumentException("config param 'aliPayRSAPublicKey' is incorrect.", e);
                }
                break;
            default:
                break;

        }
    }

    public String getInputCharset() {
        return CHARSET;
    }


}
