package com.lwh.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2  //开启swagger
public class SwaggerConfig {


    //分组：多个Docket
    @Bean
    public Docket docket1(Environment environment){
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }

    @Bean
    public Docket docket2(Environment environment){
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }

    @Bean
    public Docket docket3(Environment environment){
        return new Docket(DocumentationType.SWAGGER_2).groupName("C");
    }

    @Bean
    public Docket docket(Environment environment){

        //设置要显示的环境
        Profiles profiles = Profiles.of("dev", "test");
        //通过environment.acceptsProfiles判断项目是否处在设定的环境下
        boolean flag = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("lwh")
                //开启或关闭swagger
                .enable(flag)
                .select()
                //扫描哪些包下的请求
                .apis(RequestHandlerSelectors.basePackage("com.lwh.swagger.controller"))
                //过滤**路径下才启用
                .paths(PathSelectors.ant("/lwh/**"))
                .build();
    }

    public ApiInfo apiInfo(){
        Contact contact = new Contact("李文豪", "https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui/3.0.0", "653713704@qq.com");
        return  new ApiInfo(
                "李文豪的swagger文档",
                "知不可乎骤得",
                "1.0",
                "https://blog.csdn.net/hadues/article/details/123753888",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

}
