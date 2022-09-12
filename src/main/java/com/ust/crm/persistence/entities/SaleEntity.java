package com.ust.crm.persistence.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Table(name = "sales")
@Entity
@NoArgsConstructor
public class SaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Long id;

    @Column(name = "sale_qty")
    private Float qty;

    @OneToMany
    private List<ProductEntity> products;

    @ManyToOne
    private ClientEntity client;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}