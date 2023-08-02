package com.example.retro_cinema.products.controller;

import com.example.retro_cinema.products.service.IProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductsController {
    @Autowired
    private IProductsService productsService;
    @GetMapping
    public String showListProducts(Model model){
        model.addAttribute("productsList",productsService.getAllProducts());
        return "products/list";
    }
}
