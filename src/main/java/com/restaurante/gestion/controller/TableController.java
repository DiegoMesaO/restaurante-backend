package com.restaurante.gestion.controller;

import com.restaurante.gestion.entity.Table;
import com.restaurante.gestion.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mesas")
@CrossOrigin(origins = "*") // para permitir llamadas desde Angular
public class TableController {

    @Autowired
    private TableService tableService;

    @GetMapping
    public List<Table> listarMesas() {
        return tableService.listarMesas();
    }

    @GetMapping("/{id}")
    public Table obtenerMesa(@PathVariable Long id) {
        return tableService.obtenerPorId(id);
    }

    @PostMapping
    public Table crearMesa(@RequestBody Table table) {
        return tableService.crearMesa(table);
    }

    @PutMapping("/{id}")
    public Table actualizarMesa(@PathVariable Long id, @RequestBody Table table) {
        return tableService.actualizarMesa(id, table);
    }

    @DeleteMapping("/{id}")
    public boolean eliminarMesa(@PathVariable Long id) {
        return tableService.eliminarMesa(id);
    }
}