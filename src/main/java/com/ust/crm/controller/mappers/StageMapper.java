package com.ust.crm.controller.mappers;

import com.ust.crm.model.Stage;
import com.ust.crm.persistence.entities.StageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StageMapper {
    Stage entityToModel(StageEntity entity);
    StageEntity modelToEntity(Stage stage);
}
