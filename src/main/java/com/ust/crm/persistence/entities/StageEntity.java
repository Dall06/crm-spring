package com.ust.crm.persistence.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "stages")
@Entity
@NoArgsConstructor
public class StageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stage_id", nullable = false)
    private Long id;

    @Column(name = "stage_name", nullable = false)
    private String name;

    @Column(name = "stage_order", unique = true, nullable = false)
    private Integer order;
}
