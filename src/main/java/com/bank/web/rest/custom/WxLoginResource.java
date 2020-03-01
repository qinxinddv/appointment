package com.bank.web.rest.custom;

import com.bank.service.custom.WeiXinService;
import com.bank.service.dto.custom.SessionKeyDto;
import com.hazelcast.core.HazelcastInstance;
import com.sun.jndi.toolkit.url.UrlUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Handler;

@Api(value = "/custom/wx", tags = { "微信接口" })
@RestController
@RequestMapping("/custom/wx")
public class WxLoginResource {
    private final WeiXinService weiXinService;

    public WxLoginResource(WeiXinService weiXinService) {
        this.weiXinService = weiXinService;
    }


    @GetMapping("/login")
    @ApiOperation(value = "获取sessionKey", notes = "获取sessionKey", httpMethod = "GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "code", value = "编码", dataType = "String"),
        @ApiImplicitParam(name = "sessionKey", value = "会话key", dataType = "String")
    })
    public ResponseEntity<SessionKeyDto> login(String sessionKey, String code){
        return ResponseEntity.ok().body(weiXinService.getSessionKey(sessionKey,code));
    }
}
