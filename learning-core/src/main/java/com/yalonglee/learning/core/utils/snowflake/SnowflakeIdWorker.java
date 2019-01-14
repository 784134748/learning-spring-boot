package com.yalonglee.learning.core.utils.snowflake;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component
public class SnowflakeIdWorker {

    /**
     * 上一毫秒数
     */
    private static long lastTimestamp = -1L;
    /**
     * 毫秒内Sequence(0~4095)
     */
    private static long sequence = 0L;
    /**
     * 机器ID(0-1023)
     */
    private final long workerId;
    /**
     * 各种元数据
     */
    protected IdMeta idMeta;

    /**
     * 构造
     *
     * @param workerId 机器ID((0-1023)
     */
    public SnowflakeIdWorker(long workerId) {
        if (workerId > IdMeta.MAX_ID || workerId < 0) {
            throw new SnowflakeException(
                    String.format("worker id can't be greater than %d or less than 0", IdMeta.MAX_ID));
        }
        this.workerId = workerId;
        log.info("worker starting. timestamp left shift {}, worker id bits {}, sequence bits {}, worker id {}", IdMeta.TIMESTAMP_LEFT_SHIFT_BITS, IdMeta.ID_BITS, IdMeta.SEQUENCE_BITS, workerId);
    }

    /**
     * 生成ID（线程安全）
     *
     * @return id
     */
    public synchronized long genId() {
        long timestamp = timeGen();
        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟被修改过，回退在上一次ID生成时间之前应当抛出异常！！！
        validateTimestamp(timestamp, lastTimestamp);
        // 如果是同一时间生成的，则进行毫秒内sequence生成
        if (lastTimestamp == timestamp) {
            // Sequence和Sequence掩码进行与操作
            sequence = (sequence + 1) & IdMeta.SEQUENCE_MASK;
            // 溢出处理 (序列号超过最大值,阻塞到下一毫秒,获得新时间戳)
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 时间戳改变，毫秒内sequence重置
            sequence = 0L;
        }
        // 上次生成ID时间截
        lastTimestamp = timestamp;
        // 移位并通过或运算组成64位ID
        return ((timestamp - IdMeta.START_TIME) << IdMeta.TIMESTAMP_LEFT_SHIFT_BITS)
                | (workerId << IdMeta.ID_SHIFT_BITS)
                | sequence;
    }

    /**
     * 解析id
     * @param id
     * @return
     */
    public ID expId(long id){
        ID ret = new ID();
        ret.setSequence(id & IdMeta.SEQUENCE_MASK);
        ret.setWorker((id >>> IdMeta.SEQUENCE_BITS) & IdMeta.ID_MASK);
        ret.setTimeStamp((id >>> IdMeta.TIMESTAMP_LEFT_SHIFT_BITS) & IdMeta.TIMESTAMP_MASK);
        return ret;
    }

    /**
     * 如果当前时间戳小于上一次ID生成的时间戳，说明系统时钟被修改过，回退在上一次ID生成时间之前应当抛出异常 ！！！
     *
     * @param lastTimestamp 上一次ID生成的时间戳
     * @param timestamp     当前时间戳
     */
    private void validateTimestamp(long timestamp, long lastTimestamp) {
        if (timestamp < lastTimestamp) {
            log.error("clock is moving backwards. Rejecting requests until {}.", lastTimestamp);
            throw new SnowflakeException("Clock moved backwards. Refusing to generate id for {} milliseconds", lastTimestamp - timestamp);
        }
    }

    /**
     * 阻塞到下一毫秒,获得新时间戳
     *
     * @param lastTimestamp 上次生成ID时间截
     * @return 当前时间戳
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获取以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }
}
