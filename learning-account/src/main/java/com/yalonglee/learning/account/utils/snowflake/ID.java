package com.yalonglee.learning.account.utils.snowflake;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ID implements Serializable {

    private static final long serialVersionUid = 1L;

    /**
     * 41位时间戳（精确到毫秒，长度可用69年）
     */
    private long timeStamp;
    /**
     * 10位机器id (长度最多可支持1024个服务器节点)
     */
    private long worker;
    /**
     * 12位计数序列号（每个节点每毫秒最多生成4096个序列号）
     */
    private long sequence;

}
