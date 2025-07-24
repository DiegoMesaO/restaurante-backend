package com.restaurante.gestion.dto;

import com.restaurante.gestion.dto.ItemDetalleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CuentaDTO {
    private Integer numeroMesa;
    private List<ItemDetalleDTO> productos;
    private double total;
    private boolean pagada;
}