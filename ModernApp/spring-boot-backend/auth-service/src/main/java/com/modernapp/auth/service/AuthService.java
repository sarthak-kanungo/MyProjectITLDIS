package com.modernapp.auth.service;

import com.modernapp.auth.dto.LoginRequest;
import com.modernapp.auth.dto.LoginResponse;
import com.modernapp.auth.model.User;
import com.modernapp.auth.repository.UserRepository;
import com.modernapp.auth.security.JwtUtils;
import com.modernapp.auth.security.UserDetailsImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final CaptchaService captchaService;

    public AuthService(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtils jwtUtils,
            CaptchaService captchaService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.captchaService = captchaService;
    }

    public LoginResponse authenticate(LoginRequest request) {
        // Validate captcha
        if (!captchaService.validateCaptcha(request.getCaptcha())) {
            throw new RuntimeException("Invalid captcha");
        }

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

        return new LoginResponse(
            jwt,
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            roles,
            86400000L // 24 hours in milliseconds
        );
    }

    public LoginResponse refreshToken(String token) {
        if (jwtUtils.validateJwtToken(token)) {
            String username = jwtUtils.getUserNameFromJwtToken(token);
            User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

            List<String> roles = user.getRoles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toList());

            String newToken = jwtUtils.generateTokenFromUsername(username);

            return new LoginResponse(
                newToken,
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                roles,
                86400000L
            );
        }
        throw new RuntimeException("Invalid token");
    }
}

