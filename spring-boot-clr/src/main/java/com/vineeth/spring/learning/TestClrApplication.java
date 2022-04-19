package com.vineeth.spring.learning;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestClrApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestClrApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TestClrApplication.class, args);
    }


    @Bean
    public CommandLineRunner fizzBuzz() throws Exception {
        return args -> {
          LOGGER.info("Starting the app");
          for (int i=1; i<=100; i++){
              String result = "";
              result += i%3 == 0 ? "Fizz" : "";
              result += i%5 == 0 ? "Buzz" : "";
              LOGGER.info(result.isEmpty() ? i+"" : result);
          }
          LOGGER.info("Stopping the app");
        };
    }
}
