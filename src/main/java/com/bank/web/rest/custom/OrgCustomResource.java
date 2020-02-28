package com.bank.web.rest.custom;

import com.bank.service.OrgService;
import com.bank.service.dto.OrgDTO;
import com.bank.web.rest.OrgResource;
import io.github.jhipster.web.util.PaginationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Api(value = "/custom/org", tags = { "营业网点相关接口" })
@RestController
@RequestMapping("/custom/org")
public class OrgCustomResource {
    private final Logger log = LoggerFactory.getLogger(OrgResource.class);
    private final OrgService orgService;

    public OrgCustomResource(OrgService orgService) {
        this.orgService = orgService;
    }
    @GetMapping("/all")
    @ApiOperation(value = "营业网点查询", notes = "营业网点查询", httpMethod = "GET")
    public ResponseEntity<List<OrgDTO>> getAllOrgs() {
        log.debug("REST request to get a page of Orgs");
        List<OrgDTO> list = orgService.findAll();
        return ResponseEntity.ok().body(list);
    }
}
