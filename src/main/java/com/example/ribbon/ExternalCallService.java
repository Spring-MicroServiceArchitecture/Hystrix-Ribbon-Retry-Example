package com.example.ribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * 설명 :
 *
 * @author Hardy(조민국) / mingood92@gmail.com
 * @since 2021. 01. 14
 */
@Service
public class ExternalCallService {

    private final RestTemplate restTemplate;

    public ExternalCallService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(
            commandKey = "Server2",
            fallbackMethod = "callServer2Fallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
            }
    )
    public String callServer2() {
        URI uri = URI.create("http://external/sample/server2");
        return restTemplate.getForObject(uri, String.class);
    }

    private String callServer2Fallback() {
        return "No available";
    }

}
