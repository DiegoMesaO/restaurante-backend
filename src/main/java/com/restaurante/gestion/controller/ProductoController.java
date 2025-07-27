package com.restaurante.gestion.controller;

import com.restaurante.gestion.entity.Product;
import com.restaurante.gestion.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> listarProductos() {
        return productService.listarProductos();
    }

    @GetMapping("/{id}")
    public Product obtenerProducto(@PathVariable Long id) {
        return productService.obtenerPorId(id);
    }

    @PostMapping
    public Product crearProducto(@RequestBody Product product) {
        return productService.crearProducto(product);
    }

    @PutMapping("/{id}")
    public Product actualizarProducto(@PathVariable Long id, @RequestBody Product product) {
        return productService.actualizarProducto(id, product);
    }

    @DeleteMapping("/{id}")
    public boolean eliminarProducto(@PathVariable Long id) {
        return productService.eliminarProducto(id);
    }
}