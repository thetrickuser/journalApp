package com.thetrickuser.journalapp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class StatusController {

    @Value("${server.port}")
    String port;

    @GetMapping("status")
    public String status() {
        return "App is running on port: " + port;
    }
    

}
