package com.example.Tracker.controller;

import com.example.Tracker.entity.User;
import com.example.Tracker.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/admin")
public class AdminController {

        private final UserRepository userRepository;

        public AdminController(
                UserRepository userRepository) {

            this.userRepository = userRepository;
        }

        @GetMapping("/users")
        public List<User> getAllUsers() {
            return userRepository.findAll();
        }
    }

