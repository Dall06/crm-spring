package com.ust.crm.controller.mappers;

import com.ust.crm.model.Product;
import com.ust.crm.persistence.entities.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductEntity modelToEntity(Product product);
    Product entityToModel(ProductEntity entity);
}
