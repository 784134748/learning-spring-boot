package com.yalonglee.learning.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.yalonglee.learning.core.utils.snowflake.SnowflakeIdWorker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

@Slf4j
public class SnowflakeIdWorkerTest {
    private Set<Long> set;

    @Before
    public void setUp() {
        set = Sets.newHashSet();
    }

    @After
    public void tearDown() {
        set = null;
    }

    @Test
    public void genIdTest() {
        List<SnowflakeIdWorker> snowflakeIdWorkers = Lists.newArrayList();
        for (int i = 0; i < 32; i++) {
            SnowflakeIdWorker idWorker = new SnowflakeIdWorker(i);
            snowflakeIdWorkers.add(idWorker);
        }

        for (SnowflakeIdWorker snowflakeIdWorker : snowflakeIdWorkers) {
            IdWorkThread idWorkThread = new IdWorkThread(set, snowflakeIdWorker);
            Thread t = new Thread(idWorkThread);
            t.setDaemon(true);
            t.start();
        }

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void stressTest() throws Exception {
        loop(5000000);
        loop(5000000);
        loop(5000000);
    }

    private void loop(int idNum) {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0);
        long start = System.currentTimeMillis();
        for (int i = 0; i < idNum; i++) {
            long id = idWorker.genId();
        }
        long duration = System.currentTimeMillis() - start;
        log.info("total time:{}ms,speed is:{}/ms", duration, idNum / duration);
    }

    @AllArgsConstructor
    static class IdWorkThread implements Runnable {
        private Set<Long> set;
        private SnowflakeIdWorker snowflakeIdWorker;

        @Override
        public void run() {
            while (true) {
                long id = snowflakeIdWorker.genId();
                log.info("{}", id);
                if (!set.add(id)) {
                    log.info("duplicate:{}", id);
                }
            }
        }
    }
}
