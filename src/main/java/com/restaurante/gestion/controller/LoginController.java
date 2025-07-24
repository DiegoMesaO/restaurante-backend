package com.restaurante.gestion.controller;

import com.restaurante.gestion.entity.Usuario;
import com.restaurante.gestion.repository.UsuarioRepository;
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
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario login) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(login.getCorreo());

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (passwordEncoder.matches(login.getContrasena(), usuario.getContrasena())) {
                String token = jwtUtil.generarToken(usuario.getCorreo(), usuario.getRol());
                return ResponseEntity.ok(Map.of("token", token));
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }
}