package com.yalonglee.learning.test;

import com.google.common.collect.Sets;
import com.yalonglee.learning.core.utils.snowflake.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Slf4j
public class SnowflakeIdWorkerTest extends LearningTestApplicationTests {

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    private Set<Long> set;

    @Before
    public void setUp() {
        set = Sets.newHashSet();
    }

    @After
    public void tearDown() {
        set = null;
    }

    /**
     * 多线程是否会主键冲突
     */
    @Test
    public void genIdTest() {
        for (int i = 0; i < 10; i++) {
            IdWorkThread t = new IdWorkThread(snowflakeIdWorker,set);
            t.start();
        }
    }

    /**
     * 使用内部类的方式，将Bean注入线程
     */
    private class IdWorkThread extends Thread {
        private SnowflakeIdWorker snowflakeId;
        private Set<Long> set;

        //通过构造函数注入Bean
        public IdWorkThread(SnowflakeIdWorker snowflakeId, Set<Long> set) {
            this.snowflakeId = snowflakeId;
            this.set = set;
        }

        @Override
        public void run() {
            while (true) {
                long id = snowflakeId.genId();
                log.info("{}", id);
                if (!set.add(id)) {
                    log.info("duplicate:{}", id);
                }
            }
        }
    }

    /**
     * 测试id的生成速度
     * @throws Exception
     */
    @Test
    public void stressTest() throws Exception {
        loop(5000);
        loop(5000);
        loop(5000);
    }

    /**
     * 循环调用
     * @param idNum
     */
    private void loop(int idNum) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < idNum; i++) {
            long id = snowflakeIdWorker.genId();
            log.info("{}-{}", i, id);
        }
        long duration = System.currentTimeMillis() - start;
        log.info("total time: {}ms, speed is: {}/ms", duration, idNum / duration);
    }

}
