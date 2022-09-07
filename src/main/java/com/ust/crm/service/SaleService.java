package com.ust.crm.service;

import com.ust.crm.controller.mappers.SaleMapper;
import com.ust.crm.model.Sale;
import com.ust.crm.persistence.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SalesRepository repository;
    private final SaleMapper mapper;

    public Sale saveSale(Sale Sale) {
        return mapper.entityToModel(
                repository.save(mapper.modelToEntity(Sale))
        );
    }

    public List<Sale> getSales(){
        return repository.findAll().stream().map(mapper::entityToModel).collect(Collectors.toList());
    }

    public Optional<Sale> getSale(long idSale) {
        return repository.findById(idSale).map(mapper::entityToModel);
    }

    public void deleteSale(long idSale){
        repository.deleteById(idSale);
    }

    public Sale updateSale(Sale sale){
        return mapper.entityToModel(
                repository.save(mapper.modelToEntity(sale))
        );
    }

    public long cuenteSales(){
        return repository.count();
    }
}
