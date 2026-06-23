package com.example.Tracker.Service;

import com.example.Tracker.DTO.LoginRequest;
import com.example.Tracker.DTO.LoginResponse;
import com.example.Tracker.entity.User;
import com.example.Tracker.exception.InvalidCredentialsException;
import com.example.Tracker.repository.UserRepository;
import com.example.Tracker.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public User registerUser(User user) {

        if (userRepository.findByUsername(
                user.getUsername()).isPresent()) {

            throw new RuntimeException(
                    "Username already exists");
        }

        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword()));

        user.setRole("USER");

        return userRepository.save(user);
    }

    public LoginResponse loginUser(LoginRequest request) {

        User user = userRepository.findByUsername(
                        request.getUsername())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        boolean matches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword());

        if (!matches) {
            throw new InvalidCredentialsException("Invalid password");
        }

        String token =
                jwtUtil.generateToken(user.getUsername(),user.getRole());

        return new LoginResponse(token);
    }
}