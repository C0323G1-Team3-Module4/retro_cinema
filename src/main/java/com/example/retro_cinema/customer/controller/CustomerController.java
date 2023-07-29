package com.example.retro_cinema.customer.controller;


import com.example.retro_cinema.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService iCustomerService;

    @GetMapping("/customers")
    public String CustomerList(@PageableDefault(value = 2, sort = "id", direction = Sort.Direction.DESC)
                               Pageable pageable, @RequestParam(value = "searchByName", defaultValue = "") String searchByName, Model model) {
        model.addAttribute("customerList", iCustomerService.findAll(searchByName, pageable));
        model.addAttribute("name", searchByName);
        return "/customer/list";
    }
    @GetMapping("/info/{id}")
    public String detailCustomer(@PathVariable Integer id, Model model){
        model.addAttribute("customer",iCustomerService.findByIdCustomer(id));
        return "/customer/detail";
    }

}
