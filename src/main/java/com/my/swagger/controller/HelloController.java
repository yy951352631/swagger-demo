package com.my.swagger.controller;

import com.my.swagger.entity.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wtq
 * @date 2019/10/9 - 10:19
 */
@RestController
public class HelloController {

    @ApiOperation("hello控制类")
    @GetMapping("/hello")
    public String sayHello(@ApiParam("用户名") String name){
        return "hello world" + name;
    }

    //只要我们接口的返回值中存在实体类，这个实体类就会被swagger扫描到
    @PostMapping("/user")
    public User user(){
        return new User("lisi","123456");
    }
}
