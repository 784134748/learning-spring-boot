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
     * 碰撞检测
     */
    @Test
    public void genIdTest() {
        for (int i = 0; i < 1; i++) {
            IdWorkThread t = new IdWorkThread(snowflakeIdWorker, set);
            t.start();
        }
        //主程序循环，避免主程序退出，其他线程也随之关闭
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
                //日志输出消耗极大（无日志4000/ms，有日志6/ms）
                //log.info("{}", id);
                if (set.size() > 4000) {
                    set.clear();
                }
                if (!set.add(id)) {
                    log.info("duplicate:{}", id);
                }
            }
        }
    }

    /**
     * 测试id的生成速度
     *
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
     *
     * @param idNum
     */
    private void loop(int idNum) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < idNum; i++) {
            long id = snowflakeIdWorker.genId();
            //日志输出消耗极大（无日志4000/ms，有日志6/ms）
            //log.info("{}{}", i, id);
        }
        long duration = System.currentTimeMillis() - start;
        log.info("total time: {}ms, speed is: {}/ms", duration, idNum / duration);
    }

}
