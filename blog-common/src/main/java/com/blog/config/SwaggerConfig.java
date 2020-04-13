package com.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 功能描述：Swagger的配置类
 *
 * @author RenShiWei
 * Date: 2020/4/11 10:14
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.blog.module.business.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    /**
     * 功能描述：配置在线文档的基本信息
     * @author RenShiWei
     * Date: 2020/4/11 10:21
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("个人博客系统接口文档")
                .description("个人博客系统接口文档（author：RSW & JQJ）")
                .version("1.0")
                .build();
    }

}

