package com.restaurante.gestion.repository;

import com.restaurante.gestion.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}