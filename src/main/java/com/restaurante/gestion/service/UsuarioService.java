package com.restaurante.gestion.service;

import com.restaurante.gestion.entity.Usuario;
import com.restaurante.gestion.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario crearUsuario(Usuario usuario) {
        String contrasenaEncriptada = passwordEncoder.encode(usuario.getContrasena());
        usuario.setContrasena(contrasenaEncriptada);
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Long id, Usuario actualizado) {
        Usuario existente = obtenerPorId(id);
        if (existente == null) return null;

        existente.setNombre(actualizado.getNombre());
        existente.setCorreo(actualizado.getCorreo());
        existente.setActivo(actualizado.isActivo());
        existente.setRol(actualizado.getRol());
        existente.setTurno(actualizado.getTurno());

        return usuarioRepository.save(existente);
    }

    public boolean eliminarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}