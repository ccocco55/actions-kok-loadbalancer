package com.example.kok.common.exception.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotFoundError {
    @GetMapping("/error")
    public String error() {
        return "/main-page/404-page";
    }
}
