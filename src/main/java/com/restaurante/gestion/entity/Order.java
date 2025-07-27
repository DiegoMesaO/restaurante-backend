package com.restaurante.gestion.entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Bill bill;
}
