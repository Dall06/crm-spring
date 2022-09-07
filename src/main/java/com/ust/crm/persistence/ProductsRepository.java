package com.ust.crm.persistence;

import com.ust.crm.persistence.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductEntity, Long> {
}
