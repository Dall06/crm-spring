package com.ust.crm.persistence;

import com.ust.crm.persistence.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<ClientEntity, Long> {
}
