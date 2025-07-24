package com.restaurante.gestion.repository;

import com.restaurante.gestion.entity.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MesaRepository extends JpaRepository<Mesa, Long> {
}