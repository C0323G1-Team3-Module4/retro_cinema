package com.example.retro_cinema.customer.controller;


import com.example.retro_cinema.customer.dto.CustomerDto;
import com.example.retro_cinema.customer.model.Customer;
import com.example.retro_cinema.customer.service.ICustomerService;
import com.example.retro_cinema.user.service.account.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService iCustomerService;
    @Autowired
    private IAccountService iAccountService;
    @GetMapping("")
    public String CustomerList(@PageableDefault(value = 1, sort = "id", direction = Sort.Direction.DESC)
                               Pageable pageable, @RequestParam(value = "searchByName", defaultValue = "") String searchByName, Model model) {
        model.addAttribute("customerList", iCustomerService.findAll(searchByName, pageable));
        model.addAttribute("accountUser",iAccountService.findAll());
        model.addAttribute("searchByName", searchByName);
        return "/customer/list";
    }

    @GetMapping("/info/{id}")
    public String detailCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", iCustomerService.findByIdCustomer(id));
        return "/customer/detail";
    }

    @GetMapping("/edit/{id}")
    public String formEditCustomer(@PathVariable Integer id, Model model) {
        Customer customer = iCustomerService.findByIdCustomer(id);
        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDto);
        model.addAttribute("customerDto", customerDto);
        model.addAttribute("accountDto", iAccountService.findAll());
        return "/customer/update";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute CustomerDto customerDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Customer customer = new Customer();
        new CustomerDto().validate(customerDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/customer/update";
        }
        BeanUtils.copyProperties(customerDto, customer);
        iCustomerService.update(customer);
        redirectAttributes.addFlashAttribute("msg", "Update Customer Success!");
        return "redirect:/customers";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("idDelete") Integer id, RedirectAttributes redirectAttributes) {
        iCustomerService.delete(id);
        redirectAttributes.addFlashAttribute("msg","Delete Customer Success!");
        return "redirect:/customer";
    }
}
