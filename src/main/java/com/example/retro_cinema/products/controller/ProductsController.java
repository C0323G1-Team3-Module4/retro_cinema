package com.example.retro_cinema.products.controller;

import com.example.retro_cinema.products.dto.ProductsDto;
import com.example.retro_cinema.products.model.Products;
import com.example.retro_cinema.products.service.IProductsService;
import com.example.retro_cinema.screenings.model.Screenings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public String getAllProducts(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Products> productsList = productsService.pageProducts(pageable,true);
        model.addAttribute("productsList", productsList);
        return "products/list";
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
        redirectAttributes.addFlashAttribute("message","successfully add new");
        return "redirect:/products/list";
    }

    @PostMapping("/products/delete")
    public String deleteProducts(@RequestParam int id, RedirectAttributes redirectAttributes) {
        productsService.deleteProducts(id);
        redirectAttributes.addFlashAttribute("message","successful delete");
        return "redirect:/products/list";
    }

    @GetMapping("/products/editProduct")
    public String editProducts(@RequestParam int id, Model model) {
        Products products = productsService.findProduct(id);
        ProductsDto productsDto = new ProductsDto();
        BeanUtils.copyProperties(products, productsDto);
        model.addAttribute("productsDto", productsDto);
        model.addAttribute("id", id);
        return "products/edit";
    }

    @PostMapping("/products/edit")
    public String edit(@ModelAttribute ProductsDto productsDto,@RequestParam int id, BindingResult bindingResult, Model model,RedirectAttributes redirectAttributes) {
        Products products = new Products();
        new ProductsDto().validate(productsDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("productsDto", productsDto);
            return "products/edit";
        }
        BeanUtils.copyProperties(productsDto, products);
        Products products1 = new Products(id,products.getProductName(),products.getPrice(),products.isFlag(),products.getImg());
        productsService.editProducts(products1);
        redirectAttributes.addFlashAttribute("message","successfully edit");
        return "redirect:/products/list";
    }
}
