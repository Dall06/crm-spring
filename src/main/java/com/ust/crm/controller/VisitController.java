package com.ust.crm.controller;

import com.ust.crm.model.Visit;
import com.ust.crm.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/visit")
@RequiredArgsConstructor
public class VisitController {
    private final VisitService service;

    @GetMapping("/{id}")
    public ResponseEntity<Visit> getVisit(@PathVariable Long id){
        Optional<Visit> visitaDb = service.getVisit(id);

        if (visitaDb.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "visit not found");
        }

        return ResponseEntity.ok(visitaDb.get());
    }

    @GetMapping
    public ResponseEntity <List<Visit>> getVisits(){
        return ResponseEntity.ok(service.getVisits());
    }

    @PostMapping
    public ResponseEntity<Void> postVisit(@RequestBody Visit visit){
        Visit newVisit = service.saveVisit(visit);

        return ResponseEntity.created(URI.create(String.valueOf(newVisit.getId()))).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putVisit(@PathVariable Long id, @RequestBody Visit visit){
        service.updateVisit(visit);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisit(@PathVariable Long id){
        service.deleteVisit(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
