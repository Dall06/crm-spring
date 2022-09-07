package com.ust.crm.service;

import com.ust.crm.controller.mappers.ClientMapper;
import com.ust.crm.model.Client;
import com.ust.crm.persistence.ClientsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientsRepository repository;
    private final ClientMapper mapper;

    public Client saveClient(Client client) {
        return mapper.entityToModel(
                repository.save(mapper.modelToEntity(client))
        );
    }

    public List<Client> getClients(){
        return repository.findAll().stream().map(mapper::entityToModel).collect(Collectors.toList());
    }

    public Optional<Client> getClient(long idClient) {
        return repository.findById(idClient).map(mapper::entityToModel);
    }

    public void deleteClient(long id){
        repository.deleteById(id);
    }

    public Client updateClient(Client client){
        return mapper.entityToModel(
                repository.save(mapper.modelToEntity(client))
        );
    }

    public long countClients(){
        return repository.count();
    }
}
