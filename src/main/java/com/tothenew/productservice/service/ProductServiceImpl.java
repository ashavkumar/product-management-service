package com.tothenew.productservice.service;

import com.tothenew.productservice.exception.ResourceNotFoundException;
import com.tothenew.productservice.model.Product;
import com.tothenew.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product Product) {
        return this.productRepository.save(Product);
    }

    @Override
    public List<Product> findAllProducts() {
        List<Product> listOfProduct = productRepository.findAll().stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
        return listOfProduct;
    }

    @Override
    public Product findProductById(int id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + id));
    }

    @Override
    public Product updateProduct(int id, Product product) {
        System.out.println("update Product:Product Service Impl:Start");
        Product existingProduct = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + id));
        existingProduct.setId(product.getId());
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setFeedback(product.getFeedback());
        System.out.println("update Product:Product Service Impl:End");
        System.out.println("Existing Product:" + existingProduct);
        return productRepository.save(existingProduct);
    }

    @Override
    public List<Product> deleteProduct(int id) {
        Product existingProduct = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + id));
        this.productRepository.delete(existingProduct);
        return productRepository.findAll();
    }
}
