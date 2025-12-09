package com.example.Levelup_Backend.controller;

import com.example.Levelup_Backend.Dto.AuthRequest;
import com.example.Levelup_Backend.Dto.AuthResponse;
import com.example.Levelup_Backend.Dto.UserDto;
import com.example.Levelup_Backend.model.User;
import com.example.Levelup_Backend.service.UserService;
import com.example.Levelup_Backend.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            User user = userService.getUserByEmail(request.getEmail());
            String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
            UserDto dto = new UserDto(user.getId(), user.getNombre(), user.getEmail(), user.getRole());

            return ResponseEntity.ok(new AuthResponse(token, jwtUtil.getExpirationMs(), dto));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        // encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User created = userService.saveUser(user);
        String token = jwtUtil.generateToken(created.getEmail(), created.getRole());
        UserDto dto = new UserDto(created.getId(), created.getNombre(), created.getEmail(), created.getRole());
        return ResponseEntity.status(201).body(new AuthResponse(token, jwtUtil.getExpirationMs(), dto));
    }
}