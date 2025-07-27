package com.restaurante.gestion.repository;

import com.restaurante.gestion.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}