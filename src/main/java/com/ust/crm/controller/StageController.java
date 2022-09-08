package com.ust.crm.controller;

import com.ust.crm.model.Stage;
import com.ust.crm.service.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stage")
@RequiredArgsConstructor
public class StageController {
    private final StageService service;
    @GetMapping("/")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello to stages api route");
    }
    @GetMapping("/{id}")
    public ResponseEntity<Stage> getStage(@PathVariable Long id){
        Optional<Stage> stageDb = service.getStage(id);

        if (stageDb.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "stage not found");
        }

        return ResponseEntity.ok(stageDb.get());
    }

    @GetMapping
    public ResponseEntity <List<Stage>> getStages(){
        return ResponseEntity.ok(service.getStages());
    }

    @PostMapping
    public ResponseEntity<Void> postStage(@RequestBody Stage stage){
        Stage newStage = service.saveStage(stage);

        return ResponseEntity.created(URI.create(String.valueOf(newStage.getId()))).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putStage(@PathVariable Long id, @RequestBody Stage stage){
        service.updateStage(stage);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStage(@PathVariable Long id){
        service.deleteStage(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}