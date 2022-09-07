package com.ust.crm.persistence;

import com.ust.crm.persistence.entities.StageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StagesRepository extends JpaRepository<StageEntity, Long> {
}
