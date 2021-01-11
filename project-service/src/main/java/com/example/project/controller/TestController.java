package com.example.project.controller;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/")
    public Long ping() {
        log.info("ping");
        return System.currentTimeMillis();
    }

}
