package com.vineeth.learning.endpoints;


import org.springframework.stereotype.Component;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

@Component
@Endpoint(id="greeting")
public class GreetingEndpoint {

    @ReadOperation
    public String getGreetings() {
        return "Hello from actuator";
    }
}
