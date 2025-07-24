package com.restaurante.gestion.service;

import com.restaurante.gestion.entity.Cuenta;
import com.restaurante.gestion.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Cuenta> listarCuentas() {
        return cuentaRepository.findAll();
    }

    public Cuenta obtenerPorId(Long id) {
        return cuentaRepository.findById(id).orElse(null);
    }

    public Cuenta crearCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public Cuenta cerrarCuenta(Long id) {
        Cuenta cuenta = obtenerPorId(id);
        if (cuenta == null) return null;

        cuenta.setPagada(true);
        return cuentaRepository.save(cuenta);
    }

    public boolean eliminarCuenta(Long id) {
        if (cuentaRepository.existsById(id)) {
            cuentaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}