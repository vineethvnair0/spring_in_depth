package com.vineeth.spring.security.webservice;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Greetings {

    @GetMapping("/hello")
    public String greet(@RequestParam(name = "name")String name){
        return "hello " + name;

    }
}
