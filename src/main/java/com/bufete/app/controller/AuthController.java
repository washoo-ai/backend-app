package com.bufete.app.controller;

import com.bufete.app.dto.LoginRequest;
import com.bufete.app.model.User;
import com.bufete.app.repository.UserRepository;
import com.bufete.app.security.JwtUtil;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

 //@PostMapping("/login")
 //public ResponseEntity<?> login(@RequestBody LoginRequest request) {
 //
 //    // Buscar usuario
 //	User user = userRepository.findByUsername(request.getUsername().trim());
 //    if (user == null) {
 //        return ResponseEntity.status(401).body("Credenciales incorrectas");
 //    }
 //
 //    // Comparar contraseñas (SIN BCrypt)
 //    if (!user.getPassword().equals(request.getPassword())) {
 //        return ResponseEntity.status(401).body("Credenciales incorrectas");
 //    }
 //
 //    // Generar token con ROLE
 //    String token = jwtUtil.generateToken(user.getUsername());
 //
 //    return ResponseEntity.ok(Map.of("token", token));
 //}
 //
//
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        System.out.println("LOGIN RECIBIDO");
        System.out.println("USERNAME: " + request.getUsername());

        User user = userRepository.findByUsername(request.getUsername().trim());

        System.out.println("USUARIO DB: " + user);

        if (user == null) {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }

        if (!user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }

        String token = jwtUtil.generateToken(user.getUsername());

        System.out.println("TOKEN GENERADO");

        return ResponseEntity.ok(Map.of("token", token));
                                                                       
}}                                                                          