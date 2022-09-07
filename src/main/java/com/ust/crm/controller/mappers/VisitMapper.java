package com.ust.crm.controller.mappers;

import com.ust.crm.model.Visit;
import com.ust.crm.persistence.entities.VisitEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitMapper {
    VisitEntity modelToEntity(Visit visit);
    Visit entityToModel(VisitEntity entity);
}

