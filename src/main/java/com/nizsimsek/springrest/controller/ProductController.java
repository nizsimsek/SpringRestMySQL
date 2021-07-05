package com.nizsimsek.springrest.controller;

import com.nizsimsek.springrest.domain.Product;
import com.nizsimsek.springrest.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    @GetMapping
    List<Product> getProducts () {
        return repository.findAll();
    }

    @GetMapping("{id}")
    Optional<Product> getById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping
    Product addProduct(Product product) {
        return repository.save(product);
    }

    @PutMapping("/{id}")
    Product updateProduct(@PathVariable Long id, @RequestBody Map<String , String> body) {
        Product product = repository.getById(id);
        product.setName(body.get("name"));
        product.setPrice(body.get("price"));
        return repository.save(product);
    }

    @DeleteMapping("/{product}")
    void deleteProduct(@PathVariable Product product) {
        repository.delete(product);
    }
}
