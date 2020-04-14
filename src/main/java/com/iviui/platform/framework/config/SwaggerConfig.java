package com.iviui.platform.framework.config;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class SwaggerConfig {
    //是否开启swagger，根据环境来选择
    @Value("${swagger.enabled}")
    private Boolean swaggerShow;

    @Bean
    public Docket Lamp() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerShow)/*是否显示到swagger*/
                .apiInfo(apiInfo())
                .groupName("模块")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.iviui.platform.controller.modules"))
                .paths(/*Predicates.or(
                        PathSelectors.regex("/lamp.*"),
                        PathSelectors.regex("/lampState.*") *//*正则匹配*//*
                )*/PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes()) //增加token输入弹窗 header中增加token参数
                .securityContexts(securityContexts());//增加token输入校验弹窗 header中增加token参数
      }
    @Bean
    public Docket LampState() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerShow)
                .apiInfo(apiInfo())
                .groupName("获取Token")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.iviui.platform.controller"))
                .paths(PathSelectors.ant("/GetToken"))
                .build();
      }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("shiro-jwt-redis")
                .description("api")
                // 作者信息
                .contact(new Contact("程攀", "https://www.iviui.com", "377822835@qq.com"))
                .version("1.0.0")
                .build();
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList = new ArrayList();
        apiKeyList.add(new ApiKey("token", "token", "header"));
        return apiKeyList;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext.builder().securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$"))//过滤要验证的路径
                .build());
        return securityContexts;
    }

    //增加全局认证
    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("token", authorizationScopes));//验证增加（有许多教程说明中这个地方是Authorization,导致不能带入全局token，因为securitySchemes()方法中header写入token，所以这个地方我改为token就可以了）
        return securityReferences;
    }

}
