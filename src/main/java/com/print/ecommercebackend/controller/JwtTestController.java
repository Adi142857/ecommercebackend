package com.print.ecommercebackend.controller;

import com.print.ecommercebackend.service.JwtService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtTestController {

    private final JwtService jwtService;

    public JwtTestController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/generate")
    public String generate(@RequestParam String email) {
        return jwtService.generateToken(email);
    }

    @GetMapping("/extract")
    public String extract(@RequestParam String token) {
        return jwtService.extractEmail(token);
    }

    @GetMapping("/validate")
    public boolean validate(
            @RequestParam String token,
            @RequestParam String email) {

        return jwtService.isTokenValid(token, email);
    }
}