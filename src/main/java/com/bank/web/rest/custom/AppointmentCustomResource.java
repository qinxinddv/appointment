package com.bank.web.rest.custom;

import com.bank.domain.enumeration.AppointStateEnum;
import com.bank.domain.enumeration.LockEnum;
import com.bank.service.custom.AppointmentCustomService;
import com.bank.service.dto.custom.AppointmentApplyDto;
import com.bank.service.dto.custom.AppointmentCustomDTO;
import com.bank.service.dto.custom.AppointmentOverDto;
import com.hazelcast.core.HazelcastInstance;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.locks.Lock;

@Api(value = "/custom/appointment", tags = { "预约相关接口" })
@RestController
@RequestMapping("/custom/appointment")
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
        log.info("预约申请结束, 请求参数：{}",applyDto.toString());
    }
    @ApiOperation(value = "根据手机号查预约", notes = "根据手机号查预约", httpMethod = "GET")
    @GetMapping("/find-by-mobile")
    public ResponseEntity<Page<AppointmentCustomDTO>> findByMobile(String mobile, @PageableDefault(value = 10,page = 0,sort = {"applyTime"},direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok().body(appointmentCustomService.findByMobile(mobile,pageable));
    }

    @ApiOperation(value = "根据OpenId机构等多字段查预约", notes = "根据OpenId机构等多字段查预约", httpMethod = "GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "openId", value = "机构ID", dataType = "String",required = false),
        @ApiImplicitParam(name = "orgId", value = "机构ID", dataType = "int",example = "1",required = false),
        @ApiImplicitParam(name = "mobile", value = "手机号", dataType = "String",required = false),
        @ApiImplicitParam(name = "idCard", value = "身份证号", dataType = "String",required = false),
        @ApiImplicitParam(name = "mobile", value = "手机号", dataType = "String",required = false),
        @ApiImplicitParam(name = "state", value = "状态", dataType = "String",required = false),
        @ApiImplicitParam(name = "date", value = "预约日期", dataType = "String",required = false)
    })
    @GetMapping("/find-custom")
    public ResponseEntity<Page<AppointmentCustomDTO>> findByOrgId(String openId, Long orgId, String mobile, String idCard, AppointStateEnum state, String date, @PageableDefault(value = 10,page = 0,sort = {"applyTime"},direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok().body(appointmentCustomService.customFind(openId,orgId,mobile,idCard,state,date,pageable));
    }

    @ApiOperation(value = "预约处理状态", notes = "预约处理状态", httpMethod = "POST")
    @PostMapping("/over")
    public void over(@RequestBody @Valid AppointmentOverDto overDto){
        log.info("预约处理开始，请求参数：{}",overDto.toString());
            appointmentCustomService.over(overDto);

        log.info("预约处理结束， 请求参数：{}",overDto.toString());
    }
}
