package com.bank.task;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ILock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling
public class CommonTask {
    private static final Logger log = LoggerFactory.getLogger(CommonTask.class);
    @Autowired
    private HazelcastInstance hazelcastInstance;
    //预约资源池定时器
    @Scheduled(cron = "0/5 * * * * ?")
    //@Scheduled(fixedRate=5000)
    private void poolProcess() {
        log.info("开始执行预约资源池处理任务");
        ILock lock = hazelcastInstance.getLock("aa");
        lock.lock();
        try {
            System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        }catch (Exception e){
            log.error("");
        }
        lock.unlock();
        log.info("结束执行预约资源池处理任务");
    }
}
