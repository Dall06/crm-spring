package com.ust.crm.runners;

import com.ust.crm.controller.mappers.ProductMapper;
import com.ust.crm.model.Product;
import com.ust.crm.persistence.ProductsRepository;
import com.ust.crm.persistence.entities.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductsRunner implements CommandLineRunner {
    private final ProductsRepository productsRepository;
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Override
    public void run(String... args) throws Exception {
        ProductEntity entity1 = productMapper.modelToEntity(Product.builder()
                .name("app 1")
                .category("software")
                .price(10000)
                .registryNumber("eq3eq23ef")
                .build());

        ProductEntity entity2 = productMapper.modelToEntity(Product.builder()
                .name("app 2")
                .category("software")
                .price(10000)
                .registryNumber("eq3eq43ef")
                .build());

        ProductEntity entity3 = productMapper.modelToEntity(Product.builder()
                .name("app 3")
                .category("software")
                .price(10000)
                .registryNumber("eq22eq23ef")
                .build());

        List<ProductEntity> entities = Arrays.asList(entity1, entity2, entity3);

        productsRepository.saveAll(entities);
    }
}
