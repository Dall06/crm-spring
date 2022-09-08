package com.ust.crm.controller;

import com.ust.crm.model.Product;
import com.ust.crm.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping("/")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello to products api route");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        Optional<Product> productoDb = service.getProduct(id);

        if (productoDb.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product does ot exists");
        }

        return ResponseEntity.ok(productoDb.get());
    }

    @GetMapping
    public ResponseEntity <List<Product>> getProducts(){
        return ResponseEntity.ok(service.getProducts());
    }

    @PostMapping
    public ResponseEntity<Void> postProduct(@RequestBody Product product){
        Product newProduct = service.saveProduct(product);

        return ResponseEntity.created(URI.create(String.valueOf(newProduct.getId()))).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putProduct(@PathVariable Long id, @RequestBody Product product){
        service.updateProduct(product);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        service.deleteProduct(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}