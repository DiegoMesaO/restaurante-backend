package com.restaurante.gestion.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mesas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;

    private String ubicacion;

    private boolean disponible;
}