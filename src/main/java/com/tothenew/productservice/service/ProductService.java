package com.tothenew.productservice.service;

import com.tothenew.productservice.model.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product Product);

    List<Product> findAllProducts();

    Product findProductById(int id);

    List<Product> deleteProduct(int id);
}
