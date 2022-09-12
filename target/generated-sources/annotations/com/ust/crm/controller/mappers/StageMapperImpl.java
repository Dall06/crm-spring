package com.ust.crm.controller.mappers;

import com.ust.crm.model.Stage;
import com.ust.crm.model.Stage.StageBuilder;
import com.ust.crm.persistence.entities.StageEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-12T10:52:29-0500",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 17.0.4.1 (Amazon.com Inc.)"
)
@Component
public class StageMapperImpl implements StageMapper {

    @Override
    public Stage entityToModel(StageEntity entity) {
        if ( entity == null ) {
            return null;
        }

        StageBuilder stage = Stage.builder();

        if ( entity.getId() != null ) {
            stage.id( entity.getId() );
        }
        stage.name( entity.getName() );
        if ( entity.getOrder() != null ) {
            stage.order( entity.getOrder() );
        }

        return stage.build();
    }

    @Override
    public StageEntity modelToEntity(Stage stage) {
        if ( stage == null ) {
            return null;
        }

        StageEntity stageEntity = new StageEntity();

        stageEntity.setId( stage.getId() );
        stageEntity.setName( stage.getName() );
        stageEntity.setOrder( stage.getOrder() );

        return stageEntity;
    }
}
