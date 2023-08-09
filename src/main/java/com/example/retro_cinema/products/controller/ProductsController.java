package com.example.retro_cinema.products.controller;

import com.example.retro_cinema.products.dto.ProductsDto;
import com.example.retro_cinema.products.model.Products;
import com.example.retro_cinema.products.service.IProductsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ProductsController {
    @Autowired
    private IProductsService productsService;

    @GetMapping("/products/list")
    public String showListProducts(Model model) {
        model.addAttribute("productsList", productsService.getAllProducts());
        return "products/menu";
    }



    @GetMapping("/products/addProduct")
    public String showAddProducts(Model model) {
        model.addAttribute("productsDto", new ProductsDto());
        return "products/add";
    }

    @PostMapping("/products/add")
    public String addProducts(@Valid @ModelAttribute ProductsDto productsDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Products products = new Products();
        new ProductsDto().validate(productsDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "products/add";
        }
        BeanUtils.copyProperties(productsDto, products);
        productsService.addProducts(products);
        return "redirect:/products/list";
    }

    @PostMapping("/products/delete")
    public String deleteProducts(@RequestParam int id) {
        productsService.deleteProducts(id);
        return "redirect:/products/list";
    }

    @GetMapping("/products/editProduct")
    public String editProducts(@RequestParam int id, Model model) {
        Products products = productsService.findProduct(id);
        ProductsDto productsDto = new ProductsDto();
        BeanUtils.copyProperties(products, productsDto);
        model.addAttribute("productsDto", products);
        model.addAttribute("id",id);
        return "products/edit";
    }

    @PostMapping("/products/edit")
    public String edit(@ModelAttribute ProductsDto productsDto,BindingResult bindingResult,Model model) {
        Products products = new Products();
        new ProductsDto().validate(productsDto,bindingResult);
        if(bindingResult.hasErrors()){
            model.addAttribute("productsDto",productsDto);
            return "products/edit";
        }
        BeanUtils.copyProperties(productsDto,products);
        productsService.editProducts(products);
        return "redirect:/products/list";
    }
}
