package com.ust.crm.controller.mappers;

import com.ust.crm.model.Sale;
import com.ust.crm.persistence.entities.SaleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    SaleEntity modelToEntity(Sale sale);
    Sale entityToModel(SaleEntity entity);
}

