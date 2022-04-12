package com.vineeth.learning.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OutputService {

    private final GreetingService greetingService;

    private final TimeService timeService;

    @Value("${app.name}")
    private String name;

    public OutputService(GreetingService greetingService, TimeService timeService) {
        this.greetingService = greetingService;
        this.timeService = timeService;
    }

    public void generateOutput(){
        System.out.println(timeService.getTimeGenerated()+" "+greetingService.getGreetings(name));
    }
}
