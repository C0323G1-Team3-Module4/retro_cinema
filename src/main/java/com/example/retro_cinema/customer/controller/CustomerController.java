package com.example.retro_cinema.customer.controller;


import com.example.retro_cinema.customer.dto.CustomerDto;
import com.example.retro_cinema.customer.model.Customer;
import com.example.retro_cinema.customer.service.ICustomerService;
import com.example.retro_cinema.user.model.AccountUser;
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
    public String CustomerList(@PageableDefault(value = 5, sort = "id", direction = Sort.Direction.DESC)
                               Pageable pageable, @RequestParam(value = "searchByName", defaultValue = "") String searchByName, Model model) {
        model.addAttribute("customerList", iCustomerService.findAll(searchByName, pageable,true));
        model.addAttribute("accountUser",iAccountService.findAll());
        model.addAttribute("searchByName", searchByName);
        return "/customer/list";
    }

    @GetMapping("/info/{id}")
    public String detailCustomer(@PathVariable Integer id, Model model) {
        Customer customer = iCustomerService.findByIdCustomer(id);
        if(customer==null){
            return "/400Page";
        }
        model.addAttribute("customer", iCustomerService.findByIdCustomer(id));
        return "/customer/detail";
    }


    @PostMapping("/delete")
    public String delete(@RequestParam("idDelete") Integer id, RedirectAttributes redirectAttributes) {
        iCustomerService.delete(id);
        redirectAttributes.addFlashAttribute("msg","Delete Customer Success!");
        return "redirect:/customers";
    }

    @PostMapping("/toPreference")
    public String toPreference(@RequestParam("username") String username, Model model) {
        AccountUser accountUser = iAccountService.findByUsername(username);
        Customer customer = iCustomerService.findByIdAccount(accountUser.getId());
        model.addAttribute("customer", customer);
        return "/customer/detail";
    }
}
