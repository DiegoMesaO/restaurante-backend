package com.restaurante.gestion.service;

import com.restaurante.gestion.entity.User;
import com.restaurante.gestion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> listarUsuarios() {
        return userRepository.findAll();
    }

    public User obtenerPorId(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public User obtenerPorCorreo(String mail) {
        return userRepository.findByEmail(correo).orElse(null);
    }

    public User crearUsuario(User user) {
        String contrasenaEncriptada = passwordEncoder.encode(user.getPassword());
        user.getPassword(contrasenaEncriptada);
        return userRepository.save(user);
    }

    public User actualizarUsuario(Long id, User actualizado) {
        User existente = obtenerPorId(id);
        if (existente == null) return null;

        existente.setNombre(actualizado.getNombre());
        existente.setCorreo(actualizado.getCorreo());
        existente.setActivo(actualizado.isActivo());
        existente.setRol(actualizado.getRol());
        existente.setTurno(actualizado.getTurno());

        return userRepository.save(existente);
    }

    public boolean eliminarUsuario(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}