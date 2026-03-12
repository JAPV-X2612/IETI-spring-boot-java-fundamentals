package org.adaschool.api.controller.product;

import org.adaschool.api.exception.ProductNotFoundException;
import org.adaschool.api.repository.product.Product;
import org.adaschool.api.repository.product.ProductDto;
import org.adaschool.api.service.product.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for products endpoints
 *
 * @author Jesús Pinzón
 * @version 1.0
 * @since 2026-03-12
 */
@RestController
@RequestMapping("/v1/products/")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(@Autowired ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        Product product = new Product(productDto);
        Product savedProduct = productsService.save(product);
        URI createdProductUri = URI.create("/v1/products/" + savedProduct.getId());
        return ResponseEntity.created(createdProductUri).body(savedProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productsService.all();
        return ResponseEntity.ok(products);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") String id) {
        Optional<Product> product = productsService.findById(id);
        if (!product.isPresent()) {
            throw new ProductNotFoundException(id);
        }
        return ResponseEntity.ok(product.get());
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody ProductDto productDto) {
        Optional<Product> existingProduct = productsService.findById(id);
        if (!existingProduct.isPresent()) {
            throw new ProductNotFoundException(id);
        }
        Product product = existingProduct.get();
        product.update(productDto);
        Product updatedProduct = productsService.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
        Optional<Product> product = productsService.findById(id);
        if (!product.isPresent()) {
            throw new ProductNotFoundException(id);
        }
        productsService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
