package com.restaurante.gestion.repository;

import com.restaurante.gestion.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}