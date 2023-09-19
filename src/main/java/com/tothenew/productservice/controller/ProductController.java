package com.tothenew.productservice.controller;

import com.tothenew.productservice.model.Product;
import com.tothenew.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return this.productService.saveProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable(value = "id") int id) {
        return this.productService.findProductById(id);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return this.productService.findAllProducts();
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
        return this.productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") int id) {
        this.productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
