package com.vineeth.learning;

import com.vineeth.learning.config.ApplicationConfig;
import com.vineeth.learning.service.OutputService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        OutputService outputService = applicationContext.getBean(OutputService.class);
        for (int i=0; i<5; i++){
            outputService.generateOutput();
            Thread.sleep(5000);
        }

    }
}
