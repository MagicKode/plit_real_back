package com.plit.real.auth.controller;

import com.plit.real.auth.dto.AuthResponse;
import com.plit.real.auth.dto.LoginRequest;
import com.plit.real.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        String token = authService.authenticate(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginRequest request) {
        authService.register(request.getUsername(), request.getPassword());
        return ResponseEntity.ok("Пользователь создан");
    }

    @GetMapping("/me")
    public ResponseEntity<String> getMe(@RequestHeader("Authorization")  String token) {
        return ResponseEntity.ok("Токен работает: " + token);
    }
}
