package websocket.config;

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
    public Docket api() {
        return new Docket(DocumentationType.OAS_30) {
        }
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("websocket.controller.api")) // 대상 패키지 설정
                .paths(PathSelectors.any()) // 어떤 식으로 시작하는 api 를 보여주는지 설정: any는 전부, member/ 이면 member로 시작하는 api만 열람 가능
                // .paths(PathSelectors.ant("/chat/**"))
                // .paths(PathSelectors.ant("/v1/api/chat/createroom"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("CHAT REST API")
                .version("1.0.0")
                .description("CHAT API 문서입니다.")
                .build();
    }
}










/*
swagger2 일 경우 아래 설정


package com.simple.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.simple.controller")) // 대상 패키지 설정
                .paths(PathSelectors.any()) // 어떤 식으로 시작하는 api 를 보여주는지 설정: any는 전부, member/ 이면 member로 시작하는 api만 열람 가능
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Simple REST API")
                .version("1.0.0")
                .description("Simple api 문서입니다.")
                .build();
    }

}
 */