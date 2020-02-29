package com.bank.web.rest.custom;

import com.bank.domain.enumeration.BusiTypeEnum;
import com.bank.service.custom.AppointmentPoolCustomService;
import com.bank.service.dto.AppointmentPoolDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "/custom/appointment-pool", tags = { "预约资源池相关接口" })
@RestController
@RequestMapping("/custom/appointment-pool")
public class AppointmentPoolCustomResource {
    private final Logger log = LoggerFactory.getLogger(AppointmentPoolCustomResource.class);

    private final AppointmentPoolCustomService appointmentPoolCustomService;

    public AppointmentPoolCustomResource(AppointmentPoolCustomService appointmentPoolCustomService) {
        this.appointmentPoolCustomService = appointmentPoolCustomService;
    }

    @ApiOperation(value = "根据机构和业务类型查询", notes = "根据机构和业务类型查询", httpMethod = "GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "orgId", value = "机构ID", dataType = "Long",required = true),
        @ApiImplicitParam(name = "busiTypeEnum", value = "业务类型", dataType = "String",required = true)
    })
    @GetMapping("/find-by-org-and-busi-type")
    public ResponseEntity<List<AppointmentPoolDTO>> findByMobile(long orgId, BusiTypeEnum busiTypeEnum){
        return ResponseEntity.ok().body(appointmentPoolCustomService.findByOrgIdAndBusiType(orgId,busiTypeEnum));
    }

}
