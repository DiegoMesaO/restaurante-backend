package com.restaurante.gestion.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String correo;

    private String contrasena;

    private String rol; // Ej: "MESERO" o "ADMIN"

    private boolean activo;

    private String turno; // Puedes usar algo como "Mañana", "Tarde", o rango horario
}