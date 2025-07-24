package com.restaurante.gestion.service;

import com.restaurante.gestion.entity.Mesa;
import com.restaurante.gestion.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    public List<Mesa> listarMesas() {
        return mesaRepository.findAll();
    }

    public Mesa obtenerPorId(Long id) {
        return mesaRepository.findById(id).orElse(null);
    }

    public Mesa crearMesa(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public Mesa actualizarMesa(Long id, Mesa nuevaMesa) {
        Mesa existente = obtenerPorId(id);
        if (existente == null) return null;

        existente.setUbicacion(nuevaMesa.getUbicacion());
        existente.setDisponible(nuevaMesa.isDisponible());

        return mesaRepository.save(existente);
    }

    public boolean eliminarMesa(Long id) {
        if (mesaRepository.existsById(id)) {
            mesaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}