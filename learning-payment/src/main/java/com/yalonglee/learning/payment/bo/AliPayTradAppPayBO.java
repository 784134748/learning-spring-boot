package com.yalonglee.learning.payment.bo;

import com.alipay.api.domain.ExtUserInfo;
import com.alipay.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AliPayTradAppPayBO implements Serializable {

    private final static long serialVersionUid = 1L;

    @ApiField("body")
    private String body;

    @ApiField("subject")
    private String subject;

    @ApiField("out_trade_no")
    private String outTradeNo;

    @ApiField("timeout_express")
    private String timeoutExpress;

    @ApiField("time_express")
    private String timeExpress;

    @ApiField("total_amount")
    private String totalAmount;

    @ApiField("auth_token")
    private String authToken;

    @ApiField("product_code")
    private String productCode;

    @ApiField("goods_type")
    private String goodsType;

    @ApiField("passback_params")
    private String passbackParams;

    @ApiField("promo_params")
    private String promoParams;

    @ApiField("extend_params")
    private String extendParams;

    @ApiField("enable_pay_channels")
    private String enablePayChannels;

    @ApiField("disable_pay_channels")
    private String disablePayChannels;

    @ApiField("store_id")
    private String storeId;

    @ApiField("quit_url")
    private String quitUrl;

    @ApiField("ext_user_info")
    private ExtUserInfo extUserInfo;

}
