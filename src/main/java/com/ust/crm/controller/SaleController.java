package com.ust.crm.controller;

import com.ust.crm.model.Sale;
import com.ust.crm.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sale")
@RequiredArgsConstructor
public class SaleController {
    private final SaleService service;

    @GetMapping("/")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello to sales api route");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSale(@PathVariable(name = "id") Long id){
        Optional<Sale> ventaDb = service.getSale(id);

        if (ventaDb.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "sale not found");
        }

        return ResponseEntity.ok(ventaDb.get());
    }

    @GetMapping("/all")
    public ResponseEntity <List<Sale>> getSales(){
        return ResponseEntity.ok(service.getSales());
    }

    @PostMapping
    public ResponseEntity<Void> postSale(@RequestBody Sale sale){
        Sale newSale = service.saveSale(sale);

        return ResponseEntity.created(URI.create(String.valueOf(newSale.getId()))).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putSale(@PathVariable(name = "id") Long id, @RequestBody Sale sale){
        service.updateSale(sale);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable(name = "id") Long id){
        service.deleteSale(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}