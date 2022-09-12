package com.ust.crm.controller.mappers;

import com.ust.crm.model.Product;
import com.ust.crm.model.Product.ProductBuilder;
import com.ust.crm.persistence.entities.ProductEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-12T12:12:46-0500",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 17.0.4.1 (Amazon.com Inc.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductEntity modelToEntity(Product product) {
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

    @Override
    public Product entityToModel(ProductEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProductBuilder product = Product.builder();

        if ( entity.getId() != null ) {
            product.id( entity.getId() );
        }
        product.name( entity.getName() );
        product.category( entity.getCategory() );
        if ( entity.getPrice() != null ) {
            product.price( entity.getPrice() );
        }
        product.registryNumber( entity.getRegistryNumber() );
        product.createdAt( entity.getCreatedAt() );

        return product.build();
    }
}
