package com.ust.crm.persistence.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "clients")
@Entity
@NoArgsConstructor
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", nullable = false)
    private Long id;

    @Column(name = "client_name", nullable = false)
    private String name;

    @Column(name = "client_email", nullable = false)
    private String email;

    @Column(name = "client_employees")
    private Integer employeesQty;

    @Column(name = "client_address", nullable = false)
    private String address;
}
