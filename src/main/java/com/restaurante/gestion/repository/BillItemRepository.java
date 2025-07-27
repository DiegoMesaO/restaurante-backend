package com.restaurante.gestion.repository;

import com.restaurante.gestion.entity.BillItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillItemRepository extends JpaRepository<BillItem, Long> {
    List<BillItem> findByCuentaId(Long cuentaId);
}
