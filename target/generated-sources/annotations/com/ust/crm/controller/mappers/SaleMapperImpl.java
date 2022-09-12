package com.ust.crm.controller.mappers;

import com.ust.crm.model.Client;
import com.ust.crm.model.Client.ClientBuilder;
import com.ust.crm.model.Product;
import com.ust.crm.model.Product.ProductBuilder;
import com.ust.crm.model.Sale;
import com.ust.crm.model.Sale.SaleBuilder;
import com.ust.crm.persistence.entities.ClientEntity;
import com.ust.crm.persistence.entities.ProductEntity;
import com.ust.crm.persistence.entities.SaleEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-12T10:52:28-0500",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 17.0.4.1 (Amazon.com Inc.)"
)
@Component
public class SaleMapperImpl implements SaleMapper {

    @Override
    public SaleEntity modelToEntity(Sale sale) {
        if ( sale == null ) {
            return null;
        }

        SaleEntity saleEntity = new SaleEntity();

        saleEntity.setId( sale.getId() );
        saleEntity.setQty( sale.getQty() );
        saleEntity.setProducts( productListToProductEntityList( sale.getProducts() ) );
        saleEntity.setClient( clientToClientEntity( sale.getClient() ) );
        saleEntity.setCreatedAt( sale.getCreatedAt() );

        return saleEntity;
    }

    @Override
    public Sale entityToModel(SaleEntity entity) {
        if ( entity == null ) {
            return null;
        }

        SaleBuilder sale = Sale.builder();

        if ( entity.getId() != null ) {
            sale.id( entity.getId() );
        }
        if ( entity.getQty() != null ) {
            sale.qty( entity.getQty() );
        }
        sale.products( productEntityListToProductList( entity.getProducts() ) );
        sale.client( clientEntityToClient( entity.getClient() ) );
        sale.createdAt( entity.getCreatedAt() );

        return sale.build();
    }

    protected ProductEntity productToProductEntity(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setId( product.getId() );
        productEntity.setName( product.getName() );
        productEntity.setCategory( product.getCategory() );
        productEntity.setPrice( product.getPrice() );
        productEntity.setRegistryNumber( product.getRegistryNumber() );
        productEntity.setCreatedAt( product.getCreatedAt() );

        return productEntity;
    }

    protected List<ProductEntity> productListToProductEntityList(List<Product> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductEntity> list1 = new ArrayList<ProductEntity>( list.size() );
        for ( Product product : list ) {
            list1.add( productToProductEntity( product ) );
        }

        return list1;
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

    protected Product productEntityToProduct(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        ProductBuilder product = Product.builder();

        if ( productEntity.getId() != null ) {
            product.id( productEntity.getId() );
        }
        product.name( productEntity.getName() );
        product.category( productEntity.getCategory() );
        if ( productEntity.getPrice() != null ) {
            product.price( productEntity.getPrice() );
        }
        product.registryNumber( productEntity.getRegistryNumber() );
        product.createdAt( productEntity.getCreatedAt() );

        return product.build();
    }

    protected List<Product> productEntityListToProductList(List<ProductEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<Product> list1 = new ArrayList<Product>( list.size() );
        for ( ProductEntity productEntity : list ) {
            list1.add( productEntityToProduct( productEntity ) );
        }

        return list1;
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
