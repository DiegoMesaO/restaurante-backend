package com.restaurante.gestion.repository;

import com.restaurante.gestion.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<Table, Long> {
}