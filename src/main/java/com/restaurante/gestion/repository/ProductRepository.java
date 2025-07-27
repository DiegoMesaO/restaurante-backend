package com.restaurante.gestion.repository;

import com.restaurante.gestion.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}