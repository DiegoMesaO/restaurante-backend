package com.restaurante.gestion.controller;

import com.restaurante.gestion.dto.*;
import com.restaurante.gestion.entity.*;
import com.restaurante.gestion.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cuentas")
@RequiredArgsConstructor
public class BillController {

    private final BillRepository billRepository;
    private final BillItemRepository billItemRepository;
    private final ProductRepository productRepository;
    private final TableRepository tableRepository;
    private final UserRepository userRepository;

    // 🧾 Ver una cuenta con sus productos
    @GetMapping("/{id}")
    public ResponseEntity<CuentaDTO> verCuenta(@PathVariable Long id) {
        Bill bill = billRepository.findById(id).orElseThrow();
        List<BillItem> items = billItemRepository.findByCuentaId(id);

        List<ItemDetalleDTO> productos = items.stream()
                .map(item -> new ItemDetalleDTO(
                        item.getProduct().getNombre(),
                        item.getCantidad(),
                        item.getSubtotal()
                ))
                .collect(Collectors.toList());

        CuentaDTO dto = new CuentaDTO(
                bill.getTable().getNumero(),
                productos,
                bill.getTotal(),
                bill.isPagada()
        );

        return ResponseEntity.ok(dto);
    }

    // 🍽️ Agregar producto a cuenta
    @PostMapping("/{id}/agregar-producto")
    public ResponseEntity<?> agregarProducto(@PathVariable Long id,
                                             @RequestBody ProductoCuentaDTO dto) {

        Bill bill = billRepository.findById(id).orElseThrow();
        Product product = productRepository.findById(dto.getProductoId()).orElseThrow();

        double subtotal = product.getPrecioUnitario() * dto.getCantidad();

        BillItem item = BillItem.builder()
                .bill(bill)
                .product(product)
                .cantidad(dto.getCantidad())
                .subtotal(subtotal)
                .build();

        billItemRepository.save(item);

        bill.setTotal(bill.getTotal() + subtotal);
        billRepository.save(bill);

        return ResponseEntity.ok("Producto agregado correctamente");
    }

    // 🔓 Cerrar la cuenta y liberar la mesa
    @PutMapping("/{id}/cerrar")
    public ResponseEntity<?> cerrarCuenta(@PathVariable Long id) {
        Bill bill = billRepository.findById(id).orElseThrow();
        bill.setPagada(true);
        bill.setFechaCierre(LocalDateTime.now());

        Table table = bill.getTable();
        table.setDisponible(true);
        tableRepository.save(table);
        billRepository.save(bill);

        return ResponseEntity.ok("Cuenta cerrada y mesa liberada");
    }

    // 🪑 (Opcional) Abrir cuenta si no existe para una mesa
    @PostMapping("/abrir/{mesaId}/{meseroId}")
    public ResponseEntity<?> abrirCuenta(@PathVariable Long mesaId,
                                         @PathVariable Long meseroId) {

        Table table = tableRepository.findById(mesaId).orElseThrow();
        User mesero = userRepository.findById(meseroId).orElseThrow();

        if (!table.isDisponible()) {
            return ResponseEntity.badRequest().body("La mesa ya está ocupada");
        }

        Bill bill = Bill.builder()
                .table(table)
                .mesero(mesero)
                .total(0)
                .pagada(false)
                .fechaCreacion(LocalDateTime.now())
                .build();

        billRepository.save(bill);
        table.setDisponible(false);
        tableRepository.save(table);

        return ResponseEntity.ok("Cuenta abierta y mesa ocupada");
    }
}