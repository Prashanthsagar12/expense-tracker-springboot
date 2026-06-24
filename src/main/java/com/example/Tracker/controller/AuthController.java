package com.example.Tracker.controller;

import com.example.Tracker.DTO.LoginRequest;
import com.example.Tracker.DTO.LoginResponse;
import com.example.Tracker.entity.User;
import com.example.Tracker.Service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {

        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public LoginResponse loginUser(
            @RequestBody LoginRequest request) {
        System.out.println("LOGIN API HIT");

        return userService.loginUser(request);
    }


    @RestController
    @RequestMapping("/auth")
    public class TestController {

        @GetMapping("/test")
        public String test() {
            return "Auth endpoint working";
        }
    }
}