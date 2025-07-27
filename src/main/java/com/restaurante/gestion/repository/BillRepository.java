package com.restaurante.gestion.repository;

import com.restaurante.gestion.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}