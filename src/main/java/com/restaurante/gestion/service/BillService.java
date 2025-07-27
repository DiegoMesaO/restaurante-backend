package com.restaurante.gestion.service;

import com.restaurante.gestion.entity.Bill;
import com.restaurante.gestion.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public List<Bill> listarCuentas() {
        return billRepository.findAll();
    }

    public Bill obtenerPorId(Long id) {
        return billRepository.findById(id).orElse(null);
    }

    public Bill crearCuenta(Bill bill) {
        return billRepository.save(bill);
    }

    public Bill cerrarCuenta(Long id) {
        Bill bill = obtenerPorId(id);
        if (bill == null) return null;

        bill.setPagada(true);
        return billRepository.save(bill);
    }

    public boolean eliminarCuenta(Long id) {
        if (billRepository.existsById(id)) {
            billRepository.deleteById(id);
            return true;
        }
        return false;
    }
}