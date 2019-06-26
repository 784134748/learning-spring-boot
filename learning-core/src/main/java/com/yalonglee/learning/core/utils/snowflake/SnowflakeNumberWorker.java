package com.yalonglee.learning.core.utils.snowflake;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author yalonglee
 */
@Slf4j
@Data
@Component
public class SnowflakeNumberWorker extends AbstractSnowflakeWorker {

    /**
     * 生成ID（输出18位数字,线程安全）
     *
     * @return id
     */
    public synchronized String genNumber() {
        int mill = 1;
        int second = 1000 * mill;
        long timestamp = timeGen();
        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟被修改过，回退在上一次ID生成时间之前应当抛出异常！！！
        validateTimestamp(timestamp, lastTimestamp);
        // 如果是同一时间生成的，则进行毫秒内sequence生成
        if (lastTimestamp / second == timestamp / second) {
            // Sequence和Sequence掩码进行与操作
            sequence = (sequence + 1) & IdMeta.SEQUENCE_MASK;
            // 溢出处理 (序列号超过最大值,阻塞到下一毫秒,获得新时间戳)
            if (sequence == 0) {
                timestamp = tilNextSecond(lastTimestamp);
            }
        } else {
            // 时间戳改变，毫秒内sequence重置
            sequence = 0L;
        }
        // 上次生成ID时间截
        lastTimestamp = timestamp;

        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(timestamp / 1000, 0, ZoneOffset.ofHours(8));
        String dateTime = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));

        return String.format("%04d", workerId) + dateTime + String.format("%04d", sequence);
    }

    /**
     * 阻塞到下一秒,获得新时间戳
     *
     * @param lastTimestamp 上次生成ID时间截
     * @return 当前时间戳
     */
    private long tilNextSecond(long lastTimestamp) {
        int mill = 1;
        int second = 1000 * mill;
        long timestamp = timeGen();
        while (timestamp / second <= lastTimestamp / second) {
            timestamp = timeGen();
        }
        return timestamp;
    }

}
