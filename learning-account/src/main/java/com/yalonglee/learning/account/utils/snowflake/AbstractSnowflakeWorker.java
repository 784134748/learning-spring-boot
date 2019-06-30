package com.yalonglee.learning.account.utils.snowflake;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component
public abstract class AbstractSnowflakeWorker implements InitializingBean {

    /**
     * 上一毫秒数
     */
    protected static long lastTimestamp = -1L;
    /**
     * 毫秒内Sequence(0~4095)
     */
    protected static long sequence = 0L;
    /**
     * 机器ID(0-1023)
     */
    protected long workerId;

    @Override
    public void afterPropertiesSet() {
        //initialize worker id
        long workerIdLocal = 0L;
        if (workerId > IdMeta.MAX_ID || workerId < 0) {
            throw new SnowflakeException(
                    String.format("worker id can't be greater than %d or less than 0", IdMeta.MAX_ID));
        }
        this.workerId = workerIdLocal;
        log.info("worker starting. timestamp left shift {}, worker id bits {}, sequence bits {}, worker id {}", IdMeta.TIMESTAMP_LEFT_SHIFT_BITS, IdMeta.ID_BITS, IdMeta.SEQUENCE_BITS, workerId);
    }

    /**
     * 如果当前时间戳小于上一次ID生成的时间戳，说明系统时钟被修改过，回退在上一次ID生成时间之前应当抛出异常 ！！！
     *
     * @param lastTimestamp 上一次ID生成的时间戳
     * @param timestamp     当前时间戳
     */
    protected void validateTimestamp(long timestamp, long lastTimestamp) {
        if (timestamp < lastTimestamp) {
            log.error("clock is moving backwards. Rejecting requests until {}.", lastTimestamp);
            throw new SnowflakeException("Clock moved backwards. Refusing to generate id for {} milliseconds", lastTimestamp - timestamp);
        }
    }

    /**
     * 获取以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

}
