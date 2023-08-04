package com.example.retro_cinema.products.service;

import com.example.retro_cinema.products.model.Products;
import com.example.retro_cinema.products.repository.IProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsService implements IProductsService {
    @Autowired
    private IProductsRepository productsRepository;

    @Override
    public List<Products> getAllProducts() {
        List<Products> products = new ArrayList<>();
        List<Products> productsList = productsRepository.findAll();
        for (Products p: productsList) {
            if(p.isFlag()){
                products.add(p);
            }
        }
        return products;
    }

    @Override
    public void addProducts(Products products) {
        productsRepository.save(products);
    }

    @Override
    public void deleteProducts(int id) {
        Products products = productsRepository.findById(id).get();
        products.setFlag(false);
        productsRepository.save(products);
    }

    @Override
    public void editProducts(Products products) {
        productsRepository.save(products);
    }
}
