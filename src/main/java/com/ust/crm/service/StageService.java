package com.ust.crm.service;

import com.ust.crm.controller.mappers.StageMapper;
import com.ust.crm.model.Stage;
import com.ust.crm.persistence.StagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StageService {
    private final StagesRepository repository;
    private final StageMapper mapper;

    public Stage saveStage(Stage etapa) {
        return mapper.entityToModel(
                repository.save(mapper.modelToEntity(etapa))
        );
    }

    public List<Stage> getStages(){
        return repository.findAll().stream().map(mapper::entityToModel).collect(Collectors.toList());
    }

    public Optional<Stage> getStage(long idStage) {
        return repository.findById(idStage).map(mapper::entityToModel);
    }

    public void deleteStage(long idStage){
        repository.deleteById(idStage);
    }

    public Stage updateStage(Stage etapa){
        return mapper.entityToModel(
                repository.save(mapper.modelToEntity(etapa))
        );
    }

    public long countStages(){
        return repository.count();
    }
}
