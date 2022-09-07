package com.ust.crm.controller;

import com.ust.crm.model.Client;
import com.ust.crm.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService service;

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id){
        Optional<Client> clientDb = service.getClient(id);

        if (clientDb.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "client does not exists");
        }

        return ResponseEntity.ok(clientDb.get());
    }

    @GetMapping
    public ResponseEntity <List<Client>> getClients(){
        return ResponseEntity.ok(service.getClients());
    }

    @PostMapping
    public ResponseEntity<Void> postClient(@RequestBody Client client){
        Client newClient = service.saveClient(client);

        return ResponseEntity.created(URI.create(String.valueOf(newClient.getId()))).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putClient(@PathVariable Long id, @RequestBody Client client){
        service.updateClient(client);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        service.deleteClient(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
