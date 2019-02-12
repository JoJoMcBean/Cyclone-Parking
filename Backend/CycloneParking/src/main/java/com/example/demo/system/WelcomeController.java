package com.example.demo.system;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class WelcomeController {

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome to db";
    }
}
