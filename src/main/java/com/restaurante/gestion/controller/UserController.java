package com.restaurante.gestion.controller;

import com.restaurante.gestion.entity.User;
import com.restaurante.gestion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> listarUsuarios() {
        return userService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public User obtenerUsuario(@PathVariable Long id) {
        return userService.obtenerPorId(id);
    }

    @PostMapping("/registro")
    public User crearUsuario(@RequestBody User user) {
        return userService.crearUsuario(user);
    }

    @PutMapping("/{id}")
    public User actualizarUsuario(@PathVariable Long id, @RequestBody User user) {
        return userService.actualizarUsuario(id, user);
    }

    @DeleteMapping("/{id}")
    public boolean eliminarUsuario(@PathVariable Long id) {
        return userService.eliminarUsuario(id);
    }
}