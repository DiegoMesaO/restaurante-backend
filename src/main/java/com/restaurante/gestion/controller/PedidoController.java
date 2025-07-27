package com.restaurante.gestion.controller;

import com.restaurante.gestion.entity.Order;
import com.restaurante.gestion.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Order> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    public Order obtenerPedido(@PathVariable Long id) {
        return pedidoService.obtenerPorId(id);
    }

    @PostMapping
    public Order crearPedido(@RequestBody Order order) {
        return pedidoService.crearPedido(order);
    }

    @DeleteMapping("/{id}")
    public boolean eliminarPedido(@PathVariable Long id) {
        return pedidoService.eliminarPedido(id);
    }
}