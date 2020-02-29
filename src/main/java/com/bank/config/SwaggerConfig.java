package com.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket app_api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.ant("/custom/**")).build().groupName("预约征信查询").pathMapping("/").apiInfo(apiInfo("预约征信查询","预约征信查询","1.0"));
    }
    private ApiInfo apiInfo(String name,String description,String version) {
        ApiInfo apiInfo = new ApiInfoBuilder().title(name).description(description).version(version).build();
        return apiInfo;
    }
}
