package com.ust.crm.controller.mappers;

import com.ust.crm.model.Client;
import com.ust.crm.model.Client.ClientBuilder;
import com.ust.crm.model.Visit;
import com.ust.crm.model.Visit.VisitBuilder;
import com.ust.crm.persistence.entities.ClientEntity;
import com.ust.crm.persistence.entities.VisitEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-12T13:37:55-0500",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class VisitMapperImpl implements VisitMapper {

    @Override
    public VisitEntity modelToEntity(Visit visit) {
        if ( visit == null ) {
            return null;
        }

        VisitEntity visitEntity = new VisitEntity();

        visitEntity.setId( visit.getId() );
        visitEntity.setClient( clientToClientEntity( visit.getClient() ) );
        visitEntity.setProgrammedVisitDate( visit.getProgrammedVisitDate() );
        visitEntity.setAddress( visit.getAddress() );
        visitEntity.setPurpose( visit.getPurpose() );
        visitEntity.setSeller( visit.getSeller() );

        return visitEntity;
    }

    @Override
    public Visit entityToModel(VisitEntity entity) {
        if ( entity == null ) {
            return null;
        }

        VisitBuilder visit = Visit.builder();

        visit.id( entity.getId() );
        visit.client( clientEntityToClient( entity.getClient() ) );
        visit.programmedVisitDate( entity.getProgrammedVisitDate() );
        visit.address( entity.getAddress() );
        visit.purpose( entity.getPurpose() );
        visit.seller( entity.getSeller() );

        return visit.build();
    }

    protected ClientEntity clientToClientEntity(Client client) {
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

    protected Client clientEntityToClient(ClientEntity clientEntity) {
        if ( clientEntity == null ) {
            return null;
        }

        ClientBuilder client = Client.builder();

        if ( clientEntity.getId() != null ) {
            client.id( clientEntity.getId() );
        }
        client.name( clientEntity.getName() );
        client.email( clientEntity.getEmail() );
        if ( clientEntity.getEmployeesQty() != null ) {
            client.employeesQty( clientEntity.getEmployeesQty() );
        }
        client.address( clientEntity.getAddress() );

        return client.build();
    }
}
