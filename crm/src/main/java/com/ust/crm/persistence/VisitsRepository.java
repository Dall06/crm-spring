package com.ust.crm.persistence;


import com.ust.crm.persistence.entities.VisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitsRepository extends JpaRepository<VisitEntity, Long> {
}
