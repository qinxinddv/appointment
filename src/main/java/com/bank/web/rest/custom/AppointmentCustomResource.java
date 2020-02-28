package com.bank.web.rest.custom;

import com.bank.domain.enumeration.LockEnum;
import com.bank.service.AppointmentCustomService;
import com.bank.service.dto.custom.AppointmentApplyDto;
import com.hazelcast.core.HazelcastInstance;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.locks.Lock;

@Api(value = "/api/custom/appointment", tags = { "预约相关接口" })
@RestController
@RequestMapping("/api/custom/appointment")
public class AppointmentCustomResource {
    private final Logger log = LoggerFactory.getLogger(AppointmentCustomResource.class);
    @Autowired
    private HazelcastInstance hazelcastInstance;
    @Autowired
    private AppointmentCustomService appointmentCustomService;

    @ApiOperation(value = "预约申请", notes = "预约申请", httpMethod = "POST")
    @PostMapping("/apply")
    public void apply(@RequestBody @Valid AppointmentApplyDto applyDto){
        log.info("预约申请开始，请求参数：{}",applyDto.toString());
        Lock lock = hazelcastInstance.getLock(LockEnum.APPOINTMENT.name());
        appointmentCustomService.check(applyDto);
        lock.lock();
        try {
            appointmentCustomService.apply(applyDto);
        }catch (Exception e){
            throw e;
        }finally {
            lock.unlock();
        }
        log.info("预约申请结束，结果：{} 请求参数：{}","aaa",applyDto.toString());
    }
}
