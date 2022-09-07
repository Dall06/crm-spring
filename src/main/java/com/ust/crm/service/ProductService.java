package com.ust.crm.service;

import com.ust.crm.controller.mappers.ProductMapper;
import com.ust.crm.model.Product;
import com.ust.crm.persistence.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductsRepository repository;
    private final ProductMapper mapper;

    public Product saveProduct(Product product) {
        return mapper.entityToModel(
                repository.save(mapper.modelToEntity(product))
        );
    }

    public List<Product> getProducts(){
        return repository.findAll().stream().map(mapper::entityToModel).collect(Collectors.toList());
    }

    public Optional<Product> getProduct(long idProduct) {
        return repository.findById(idProduct).map(mapper::entityToModel);
    }

    public void deleteProduct(long idProduct){
        repository.deleteById(idProduct);
    }

    public Product updateProduct(Product product){
        return mapper.entityToModel(
                repository.save(mapper.modelToEntity(product))
        );
    }

    public long countProducts(){
        return repository.count();
    }
}
