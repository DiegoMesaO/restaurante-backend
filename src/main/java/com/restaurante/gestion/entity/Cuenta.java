package com.restaurante.gestion.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cuentas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double total;

    private boolean pagada;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaCierre;

    @ManyToOne
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario mesero;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    private List<ItemCuenta> items;
}