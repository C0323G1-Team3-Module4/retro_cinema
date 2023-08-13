package com.example.retro_cinema.home.controller;

import com.example.retro_cinema.customer.dto.CustomerDto;
import com.example.retro_cinema.customer.model.Customer;
import com.example.retro_cinema.customer.service.ICustomerService;
import com.example.retro_cinema.user.service.account.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class CustomerUserController {
    @Autowired
    private ICustomerService iCustomerService;
    @Autowired
    private IAccountService iAccountService;
    @GetMapping("/customer/info/{username}")
    public String info(@PathVariable String username, Model model){
        Customer customer = iCustomerService.findByNameCustomer(username);
        if(customer==null){
            return "/400Page";
        }
        model.addAttribute("customer", iCustomerService.findByNameCustomer(username));
        return "/user/detail";
    }

        @GetMapping("/customer/edit/{id}/{username}")
    public String formEditCustomer(@PathVariable Integer id, @PathVariable String username, Model model) {
        Customer customer = iCustomerService.findByIdCustomer(id);
        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDto);
        model.addAttribute("customerDto", customerDto);
        model.addAttribute("accountDto", iAccountService.findAll());
        model.addAttribute("username",username);
        return "/user/update";
    }

    @PostMapping("/customer/update")
    public String update(@Valid @ModelAttribute CustomerDto customerDto, @RequestParam String username, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Customer customer = new Customer();
        new CustomerDto().validate(customerDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/user/update";
        }
        BeanUtils.copyProperties(customerDto, customer);
        iCustomerService.update(customer);
        redirectAttributes.addFlashAttribute("message", "Update Customer Success!");
        String url = "redirect:/customer/info/"+ username ;
        return url;
    }

}
