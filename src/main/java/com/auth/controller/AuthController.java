package com.auth.controller;

import com.auth.dto.LoginRequest;
import com.auth.dto.LoginResponse;
import com.auth.dto.SignupRequest;
import com.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public String signup(@Valid @RequestBody SignupRequest request){
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request){
       return authService.login(request);
    }

    //Checking the logged in user for testing
    @GetMapping("/test")
    public String test() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
