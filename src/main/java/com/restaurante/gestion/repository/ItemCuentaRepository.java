package com.restaurante.gestion.repository;

import com.restaurante.gestion.entity.ItemCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCuentaRepository extends JpaRepository<ItemCuenta, Long> {
    List<ItemCuenta> findByCuentaId(Long cuentaId);
}
