package com.ust.crm.persistence.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Table(name = "products")
@Entity
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "product_category", nullable = false)
    private String category;

    @Column(name = "product_price", nullable = false)
    private Float price;

    @Column(name = "product_registry_number", length = 20)
    private String registryNumber;

    @Column(name = "product_created_at")
    private LocalDate createdAt;
}
