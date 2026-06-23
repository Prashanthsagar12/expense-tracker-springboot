package com.example.Tracker.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("JWT FILTER EXECUTED");

        String authHeader =
                request.getHeader("Authorization");

        System.out.println("AUTH HEADER = " + authHeader);

        if (authHeader != null
                && authHeader.startsWith("Bearer ")) {

            System.out.println("AUTH HEADER = " + authHeader);

            String token =
                    authHeader.substring(7);

            System.out.println("TOKEN = " + token);

            boolean valid =
                    jwtUtil.validateToken(token);

            System.out.println("VALID = " + valid);

            if (!valid) {

                response.setStatus(
                        HttpServletResponse.SC_UNAUTHORIZED);

                response.getWriter()
                        .write("Invalid JWT Token");

                return;
            }

            String username =
                    jwtUtil.extractUsername(token);

            System.out.println("USERNAME = " + username);

                           java.util.Collections.emptyList();

            String role =
                    jwtUtil.extractRole(token);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            java.util.List.of(
                                    new org.springframework.security.core.authority
                                            .SimpleGrantedAuthority(
                                            "ROLE_" + role)));

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);

            System.out.println("AUTHENTICATION SET");
        }
        filterChain.doFilter(request, response);
    }
}