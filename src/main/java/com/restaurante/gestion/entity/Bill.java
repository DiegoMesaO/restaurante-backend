package com.restaurante.gestion.entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bills")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double total;

    private boolean pagada;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaCierre;

    @ManyToOne
    @JoinColumn(name = "mesa_id")
    private Table table;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User mesero;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    private List<BillItem> items;
}