package com.ust.crm.service;

import com.ust.crm.controller.mappers.VisitMapper;
import com.ust.crm.model.Visit;
import com.ust.crm.persistence.VisitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitService {
    private final VisitsRepository repository;
    private final VisitMapper mapper;

    public Visit saveVisit(Visit visit) {
        return mapper.entityToModel(
                repository.save(mapper.modelToEntity(visit))
        );
    }

    public List<Visit> getVisits(){
        return repository.findAll().stream().map(mapper::entityToModel).collect(Collectors.toList());
    }

    public Optional<Visit> getVisit(long idVisit) {
        return repository.findById(idVisit).map(mapper::entityToModel);
    }

    public void deleteVisit(long idVisit){
        repository.deleteById(idVisit);
    }

    public Visit updateVisit(Visit visit){
        return mapper.entityToModel(
                repository.save(mapper.modelToEntity(visit))
        );
    }

    public long countVisits(){
        return repository.count();
    }
}
