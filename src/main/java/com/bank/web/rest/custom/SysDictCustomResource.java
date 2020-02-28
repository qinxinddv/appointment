package com.bank.web.rest.custom;

import com.bank.service.SysDictService;
import com.bank.service.dto.custom.SysDictTreeNodeDto;
import com.bank.web.rest.AppointmentConfigResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "/custom/sys-dict", tags = { "数据字典接口" })
@RestController
@RequestMapping("/custom/sys-dict")
public class SysDictCustomResource {
    private final Logger log = LoggerFactory.getLogger(AppointmentConfigResource.class);
    @Autowired
    private SysDictService sysDictService;

    @GetMapping("/sys-dicts-by-type/{type}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "type", value = "字典数据类型", dataType = "String")
    })
    public List<SysDictTreeNodeDto> getSysDictByType(@PathVariable String type) {
        log.debug("REST request to get SysDict : {}", type);
        List<SysDictTreeNodeDto> sysDictList=sysDictService.findDistinctByType(type);
        return sysDictList;
    }
}
