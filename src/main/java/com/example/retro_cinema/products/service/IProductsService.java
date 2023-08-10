package com.example.retro_cinema.products.service;

import com.example.retro_cinema.products.model.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductsService {
    List<Products> getAllProducts();
    void addProducts(Products products);
    void deleteProducts(int id);
    void editProducts(Products products);
    Products findProduct(int id);
    Page<Products> pageProducts(Pageable pageable,boolean flag);
}
