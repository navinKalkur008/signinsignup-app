package com.auth.service.impl;

import com.auth.dto.LoginRequest;
import com.auth.dto.LoginResponse;
import com.auth.dto.SignupRequest;
import com.auth.entity.User;
import com.auth.exception.InvalidPasswordException;
import com.auth.exception.UserAlreadyExistsException;
import com.auth.exception.UserNotFoundException;
import com.auth.repository.UserRepository;
import com.auth.security.JwtUtil;
import com.auth.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String register(SignupRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists!!");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return "User Registered Successfully";
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        //Check User Exists
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new UserNotFoundException("User Not Found!"));

        //Check the Password match
        if (! passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid Password");
        }

        //Generate JWT Token
        String token = jwtUtil.generateToken(user.getEmail());

        //Return Token
        return new LoginResponse(token);
    }
}
