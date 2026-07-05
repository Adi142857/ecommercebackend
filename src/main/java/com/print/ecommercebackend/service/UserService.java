package com.print.ecommercebackend.service;

import com.print.ecommercebackend.dto.LoginRequest;
import com.print.ecommercebackend.entity.User;
import com.print.ecommercebackend.exception.EmailAlreadyExistsException;
import com.print.ecommercebackend.exception.InvalidCredentialsException;
import com.print.ecommercebackend.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;
private final JwtService jwtService;

    public UserService(UserRepository userRepository,
                    PasswordEncoder passwordEncoder,
                    JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    public User register(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail());

        if (user == null) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return jwtService.generateToken(user.getEmail());
    }
}