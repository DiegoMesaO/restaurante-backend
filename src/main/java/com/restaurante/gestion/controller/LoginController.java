package com.restaurante.gestion.controller;

import com.restaurante.gestion.entity.User;
import com.restaurante.gestion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.restaurante.gestion.security.JwtUtil;


import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User login) {
        Optional<User> usuarioOpt = userRepository.findByEmail(login.getCorreo());

        if (usuarioOpt.isPresent()) {
            User user = usuarioOpt.get();
            if (passwordEncoder.matches(login.getContrasena(), user.getContrasena())) {
                String token = jwtUtil.generarToken(user.getCorreo(), user.getRol());
                return ResponseEntity.ok(Map.of("token", token));
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }
}