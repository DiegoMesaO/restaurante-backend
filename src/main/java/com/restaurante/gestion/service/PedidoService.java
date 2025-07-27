package com.restaurante.gestion.service;

import com.restaurante.gestion.entity.Order;
import com.restaurante.gestion.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> listarPedidos() {
        return orderRepository.findAll();
    }

    public Order obtenerPorId(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order crearPedido(Order order) {
        return orderRepository.save(order);
    }

    public boolean eliminarPedido(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}