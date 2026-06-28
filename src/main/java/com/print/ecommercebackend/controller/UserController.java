package com.print.ecommercebackend.controller;

import com.print.ecommercebackend.dto.LoginRequest;
import com.print.ecommercebackend.dto.RegisterRequest;
import com.print.ecommercebackend.entity.User;
import com.print.ecommercebackend.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
}