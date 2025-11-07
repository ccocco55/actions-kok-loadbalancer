package com.example.kok.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/service/**")
public class ServiceController {
    @GetMapping("kok-service")
    public String kokService(){
        return "service/kok-service";
    }
}
