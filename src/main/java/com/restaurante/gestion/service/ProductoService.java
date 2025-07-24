package com.restaurante.gestion.service;

import com.restaurante.gestion.entity.Producto;
import com.restaurante.gestion.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(Long id, Producto actualizado) {
        Producto existente = obtenerPorId(id);
        if (existente == null) return null;

        existente.setNombre(actualizado.getNombre());
        existente.setCategoria(actualizado.getCategoria());
        existente.setCantidadDisponible(actualizado.getCantidadDisponible());
        existente.setPrecioUnitario(actualizado.getPrecioUnitario());

        return productoRepository.save(existente);
    }

    public boolean eliminarProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}