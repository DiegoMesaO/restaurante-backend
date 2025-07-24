package com.restaurante.gestion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDetalleDTO {
    private String nombreProducto;
    private int cantidad;
    private double subtotal;
}
