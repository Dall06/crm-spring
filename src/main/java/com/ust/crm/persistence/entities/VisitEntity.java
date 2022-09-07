package com.ust.crm.persistence.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table(name = "visits")
@Entity
@NoArgsConstructor
public class VisitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_id")
    private long id;

    @ManyToOne
    private ClientEntity client;

    @Column(name = "visit_date", nullable = false)
    private LocalDateTime programmedVisitDate;

    @Column(name = "visit_address", nullable = false)
    private String address;

    @Column(name = "visit_purpose", nullable = false)
    private String purpose;

    @Column(name = "visit_seller", nullable = false)
    private String seller;
}
