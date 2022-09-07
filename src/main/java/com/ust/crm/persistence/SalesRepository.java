package com.ust.crm.persistence;

import com.ust.crm.persistence.entities.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<SaleEntity, Long>  {
}
