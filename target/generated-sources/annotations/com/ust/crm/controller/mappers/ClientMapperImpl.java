package com.ust.crm.controller.mappers;

import com.ust.crm.model.Client;
import com.ust.crm.model.Client.ClientBuilder;
import com.ust.crm.persistence.entities.ClientEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-12T10:52:29-0500",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 17.0.4.1 (Amazon.com Inc.)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientEntity modelToEntity(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientEntity clientEntity = new ClientEntity();

        clientEntity.setId( client.getId() );
        clientEntity.setName( client.getName() );
        clientEntity.setEmail( client.getEmail() );
        clientEntity.setEmployeesQty( client.getEmployeesQty() );
        clientEntity.setAddress( client.getAddress() );

        return clientEntity;
    }

    @Override
    public Client entityToModel(ClientEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ClientBuilder client = Client.builder();

        if ( entity.getId() != null ) {
            client.id( entity.getId() );
        }
        client.name( entity.getName() );
        client.email( entity.getEmail() );
        if ( entity.getEmployeesQty() != null ) {
            client.employeesQty( entity.getEmployeesQty() );
        }
        client.address( entity.getAddress() );

        return client.build();
    }
}
