package com.example.retro_cinema.products.service;

import com.example.retro_cinema.products.model.Products;

import java.util.List;

public interface IProductsService {
    List<Products> getAllProducts();
    void addProducts(Products products);
    void deleteProducts(int id);
    void editProducts(Products products);
}
