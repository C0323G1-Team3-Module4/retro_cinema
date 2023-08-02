package com.example.retro_cinema.products.service;

import com.example.retro_cinema.products.model.Products;
import com.example.retro_cinema.products.repository.IProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductsService implements IProductsService{
    @Autowired
    private IProductsRepository productsRepository;
    @Override
    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }
}
