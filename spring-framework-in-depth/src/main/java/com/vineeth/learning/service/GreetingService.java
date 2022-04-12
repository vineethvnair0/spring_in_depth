package com.vineeth.learning.service;


import com.vineeth.learning.aspect.Loggable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {


    @Value("${app.greeting}")
    private String greet;


    @Loggable
    public String getGreetings(String name){
        return greet+" "+name;

    }
}
