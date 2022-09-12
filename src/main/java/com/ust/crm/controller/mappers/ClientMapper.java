package com.ust.crm.controller.mappers;

import com.ust.crm.model.Client;
import com.ust.crm.persistence.entities.ClientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientEntity modelToEntity(Client client);
    Client entityToModel(ClientEntity entity);
}
