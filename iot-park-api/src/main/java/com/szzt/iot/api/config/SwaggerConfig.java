/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.api.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            //加了ApiOperation注解的类，才生成接口文档
            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
            //包下的类，才生成接口文档
            //.apis(RequestHandlerSelectors.basePackage("com.szzt.iot.controller"))
            .paths(PathSelectors.any())
            .build()
            .directModelSubstitute(java.util.Date.class, String.class)
            .securitySchemes(security());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("证通电子")
            .description("iot-api模块接口文档")
            .termsOfServiceUrl("https://www.szzt.com.cn")
            .version("2.0")
            .build();
    }

    private List<ApiKey> security() {
        return newArrayList(
            new ApiKey("token", "token", "header")
        );
    }

}