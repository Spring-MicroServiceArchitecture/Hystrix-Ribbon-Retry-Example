package com.example.ribbon;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 설명 :
 *
 * @author Hardy(조민국) / dev.mingood@dreamus.io
 * @since 2021. 01. 14
 */
@RestController
@RequestMapping("sample")
public class SampleController {

    private final ExternalCallService externalCallService;

    public SampleController(ExternalCallService externalCallService) {
        this.externalCallService = externalCallService;
    }

    @GetMapping("/call-server2-fallback")
    public String callServer2Fallback() {
        return externalCallService.callServer2();
    }

}
