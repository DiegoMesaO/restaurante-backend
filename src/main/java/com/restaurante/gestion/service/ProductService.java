package com.restaurante.gestion.service;

import com.restaurante.gestion.entity.Product;
import com.restaurante.gestion.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> listarProductos() {
        return productRepository.findAll();
    }

    public Product obtenerPorId(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product crearProducto(Product product) {
        return productRepository.save(product);
    }

    public Product actualizarProducto(Long id, Product actualizado) {
        Product existente = obtenerPorId(id);
        if (existente == null) return null;

        existente.setNombre(actualizado.getNombre());
        existente.setCategoria(actualizado.getCategoria());
        existente.setCantidadDisponible(actualizado.getCantidadDisponible());
        existente.setPrecioUnitario(actualizado.getPrecioUnitario());

        return productRepository.save(existente);
    }

    public boolean eliminarProducto(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}