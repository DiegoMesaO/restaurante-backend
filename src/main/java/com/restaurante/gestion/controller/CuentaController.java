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
public class CuentaController {

    private final CuentaRepository cuentaRepository;
    private final ItemCuentaRepository itemCuentaRepository;
    private final ProductoRepository productoRepository;
    private final MesaRepository mesaRepository;
    private final UsuarioRepository usuarioRepository;

    // 🧾 Ver una cuenta con sus productos
    @GetMapping("/{id}")
    public ResponseEntity<CuentaDTO> verCuenta(@PathVariable Long id) {
        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow();
        List<ItemCuenta> items = itemCuentaRepository.findByCuentaId(id);

        List<ItemDetalleDTO> productos = items.stream()
                .map(item -> new ItemDetalleDTO(
                        item.getProducto().getNombre(),
                        item.getCantidad(),
                        item.getSubtotal()
                ))
                .collect(Collectors.toList());

        CuentaDTO dto = new CuentaDTO(
                cuenta.getMesa().getNumero(),
                productos,
                cuenta.getTotal(),
                cuenta.isPagada()
        );

        return ResponseEntity.ok(dto);
    }

    // 🍽️ Agregar producto a cuenta
    @PostMapping("/{id}/agregar-producto")
    public ResponseEntity<?> agregarProducto(@PathVariable Long id,
                                             @RequestBody ProductoCuentaDTO dto) {

        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow();
        Producto producto = productoRepository.findById(dto.getProductoId()).orElseThrow();

        double subtotal = producto.getPrecioUnitario() * dto.getCantidad();

        ItemCuenta item = ItemCuenta.builder()
                .cuenta(cuenta)
                .producto(producto)
                .cantidad(dto.getCantidad())
                .subtotal(subtotal)
                .build();

        itemCuentaRepository.save(item);

        cuenta.setTotal(cuenta.getTotal() + subtotal);
        cuentaRepository.save(cuenta);

        return ResponseEntity.ok("Producto agregado correctamente");
    }

    // 🔓 Cerrar la cuenta y liberar la mesa
    @PutMapping("/{id}/cerrar")
    public ResponseEntity<?> cerrarCuenta(@PathVariable Long id) {
        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow();
        cuenta.setPagada(true);
        cuenta.setFechaCierre(LocalDateTime.now());

        Mesa mesa = cuenta.getMesa();
        mesa.setDisponible(true);
        mesaRepository.save(mesa);
        cuentaRepository.save(cuenta);

        return ResponseEntity.ok("Cuenta cerrada y mesa liberada");
    }

    // 🪑 (Opcional) Abrir cuenta si no existe para una mesa
    @PostMapping("/abrir/{mesaId}/{meseroId}")
    public ResponseEntity<?> abrirCuenta(@PathVariable Long mesaId,
                                         @PathVariable Long meseroId) {

        Mesa mesa = mesaRepository.findById(mesaId).orElseThrow();
        Usuario mesero = usuarioRepository.findById(meseroId).orElseThrow();

        if (!mesa.isDisponible()) {
            return ResponseEntity.badRequest().body("La mesa ya está ocupada");
        }

        Cuenta cuenta = Cuenta.builder()
                .mesa(mesa)
                .mesero(mesero)
                .total(0)
                .pagada(false)
                .fechaCreacion(LocalDateTime.now())
                .build();

        cuentaRepository.save(cuenta);
        mesa.setDisponible(false);
        mesaRepository.save(mesa);

        return ResponseEntity.ok("Cuenta abierta y mesa ocupada");
    }
}