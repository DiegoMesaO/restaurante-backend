package com.restaurante.gestion.entity;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "tables")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;

    private String ubicacion;

    private boolean disponible;
}