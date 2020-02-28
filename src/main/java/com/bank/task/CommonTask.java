package com.bank.task;

import com.bank.domain.AppointmentPool;
import com.bank.domain.enumeration.LockEnum;
import com.bank.repository.AppointmentConfigRepository;
import com.bank.repository.AppointmentPoolRepository;
import com.bank.repository.OrgRepository;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ILock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
@EnableScheduling
@Transactional
public class CommonTask {
    private static final Logger log = LoggerFactory.getLogger(CommonTask.class);
    public static final int DAYS = 7;
    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Autowired
    private AppointmentConfigRepository appointmentConfigRepository;
    @Autowired
    private OrgRepository orgRepository;

    @Autowired
    private AppointmentPoolRepository appointmentPoolRepository;

    //预约资源池定时器,凌晨1点执行
    @Scheduled(cron = "0/59 * * * * ?")
    private void poolProcess() {
        log.info("开始执行预约资源池处理任务");
        ILock lock = hazelcastInstance.getLock(LockEnum.POOL_TASK.name());
        lock.lock();
        try {
            System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
            //根据当前日期后七天，查看是否有预约池记录
            LocalDate now = LocalDate.now();
            cleanPool(now);
            orgRepository.findAll().stream().forEach(org -> {
                log.info("机构：{}",org);
                for (int i = 0; i < DAYS; i++) {
                    String dateStr = now.plusDays(i).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    log.info("日期：{}", dateStr);
                    if (appointmentPoolRepository.countByDateAndOrg_Id(dateStr,org.getId()) > 0) {
                        continue;
                    }
                    appointmentConfigRepository.findAll().stream().forEach(appointmentConfig -> {

                        AppointmentPool pool = new AppointmentPool(dateStr, appointmentConfig.getPeriod(), appointmentConfig.getNum(), appointmentConfig.getBusiType(),org);
                        appointmentPoolRepository.save(pool);
                        log.info("初始化预约池成功，日期:{},类型：{}", dateStr, appointmentConfig.getBusiType());
                    });
                }
            });
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        log.info("结束执行预约资源池处理任务");
    }

    /**
     * 删除过期数据
     */
    private void cleanPool(LocalDate now) {
        appointmentPoolRepository.findByDateLessThan(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).stream().forEach(bean -> {
            log.info("删除过期数据：日期：{}", bean.getDate());
            appointmentPoolRepository.delete(bean);
        });
    }
}
