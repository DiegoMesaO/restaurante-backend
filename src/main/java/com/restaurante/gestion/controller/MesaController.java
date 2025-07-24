package com.restaurante.gestion.controller;

import com.restaurante.gestion.entity.Mesa;
import com.restaurante.gestion.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mesas")
@CrossOrigin(origins = "*") // para permitir llamadas desde Angular
public class MesaController {

    @Autowired
    private MesaService mesaService;

    @GetMapping
    public List<Mesa> listarMesas() {
        return mesaService.listarMesas();
    }

    @GetMapping("/{id}")
    public Mesa obtenerMesa(@PathVariable Long id) {
        return mesaService.obtenerPorId(id);
    }

    @PostMapping
    public Mesa crearMesa(@RequestBody Mesa mesa) {
        return mesaService.crearMesa(mesa);
    }

    @PutMapping("/{id}")
    public Mesa actualizarMesa(@PathVariable Long id, @RequestBody Mesa mesa) {
        return mesaService.actualizarMesa(id, mesa);
    }

    @DeleteMapping("/{id}")
    public boolean eliminarMesa(@PathVariable Long id) {
        return mesaService.eliminarMesa(id);
    }
}