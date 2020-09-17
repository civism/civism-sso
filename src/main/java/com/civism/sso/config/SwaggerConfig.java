package com.civism.sso.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "swagger", value = {"enable"}, havingValue = "true")
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("token").description("令牌")
                .modelRef(new ModelRef("string")).parameterType("header").required(true).build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xc.info.controller"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(pars);
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("基础信息管理")
                .description("基础信息接口文档")
                .version("2.0.0")
                .build();
    }

}
