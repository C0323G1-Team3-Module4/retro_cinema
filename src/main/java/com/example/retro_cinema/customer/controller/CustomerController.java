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

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService iCustomerService;

    @GetMapping("/customers")
    public String CustomerList(@PageableDefault(value = 1, sort = "id", direction = Sort.Direction.DESC)
                               Pageable pageable, @Param("name") String fullName, Model model) {
        model.addAttribute("customerList", iCustomerService.findByCustomer(fullName, pageable));
        model.addAttribute("name", fullName);
        return "/customer/list";
    }
    @GetMapping("/info/{id}")
    public String detailCustomer(@PathVariable Integer id, Model model){
        model.addAttribute("passenger",iCustomerService.findByIdCustomer(id));
        return "/customer/detail";
    }

}
