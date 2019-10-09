package com.my.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author Wtq
 * @date 2019/10/9 - 10:20
 */
@Configuration
@EnableSwagger2 //开启swagger2
public class SwaggerConfig {
    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }
    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("C");
    }


    @Bean
    public Docket docker(Environment environment){

        //设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev");
        //通过environment.acceptsProfiles 判断是否处于自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2) // 通过Docket获取SWAGGER_2的bean实例
                .apiInfo(apiInfo())
                .enable(flag)
                .groupName("张三")
                //RequestHandlerSelectors--->配置要扫描接口的方式
                //basePackage --->指定要扫描的包
                //any --->扫描全部
                //none -- > 不进行扫描
                //withClassAnnotation -->扫描类上的注解，参数是一个注解的反射对象
                //withMethodAnnotation -->扫描方法上的注解
                //.select().apis(RequestHandlerSelectors.basePackage("com.my.swagger.controller"))
                //.select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .select().apis(RequestHandlerSelectors.any())
                //paths() 过滤一个路径
//                .paths(PathSelectors.ant("/hello/**"))
                .build();
    }

    private ApiInfo apiInfo(){
        //作者信息
        Contact DEFAULT_CONTACT = new Contact("wtq", "wtq.com", "9966@qq.com");
        //配置我们的swagger信息
        return new  ApiInfo("API文档", "我的API文档", "v1.0", "urn:tos",
                DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());

    }
}
