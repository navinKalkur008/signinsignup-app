package com.auth.service;

import com.auth.dto.LoginRequest;
import com.auth.dto.LoginResponse;
import com.auth.dto.SignupRequest;

public interface AuthService {

    public String register(SignupRequest request);

    public LoginResponse login(LoginRequest request);
}
