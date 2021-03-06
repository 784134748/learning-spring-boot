package com.yalonglee.learning.core.utils.snowflake;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author yalonglee
 */
@Slf4j
@Data
@Component
public class SnowflakeIdWorker extends AbstractSnowflakeWorker {

    /**
     * 生成ID（输出18位数字,线程安全）
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
     * 解析ID
     *
     * @param id
     * @return
     */
    public ID expId(long id) {
        return ID.builder()
                .worker((id >>> IdMeta.SEQUENCE_BITS) & IdMeta.ID_MASK)
                .sequence(((id >>> IdMeta.TIMESTAMP_LEFT_SHIFT_BITS) & IdMeta.TIMESTAMP_MASK) + IdMeta.START_TIME)
                .sequence(id & IdMeta.SEQUENCE_MASK)
                .build();
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
}
