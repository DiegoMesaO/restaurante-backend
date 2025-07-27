package com.restaurante.gestion.service;

import com.restaurante.gestion.entity.Table;
import com.restaurante.gestion.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    public List<Table> listarMesas() {
        return tableRepository.findAll();
    }

    public Table obtenerPorId(Long id) {
        return tableRepository.findById(id).orElse(null);
    }

    public Table crearMesa(Table table) {
        return tableRepository.save(table);
    }

    public Table actualizarMesa(Long id, Table nuevaTable) {
        Table existente = obtenerPorId(id);
        if (existente == null) return null;

        existente.setUbicacion(nuevaTable.getUbicacion());
        existente.setDisponible(nuevaTable.isDisponible());

        return tableRepository.save(existente);
    }

    public boolean eliminarMesa(Long id) {
        if (tableRepository.existsById(id)) {
            tableRepository.deleteById(id);
            return true;
        }
        return false;
    }
}