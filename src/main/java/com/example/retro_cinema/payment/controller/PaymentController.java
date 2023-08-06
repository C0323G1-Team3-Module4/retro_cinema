package com.example.retro_cinema.payment.controller;

import com.example.retro_cinema.customer.service.ICustomerService;
import com.example.retro_cinema.seats.service.ISeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("payment")
public class PaymentController {
    @Autowired
    private ICustomerService iCustomerService;
    @Autowired
    private ISeatsService iSeatsService;

}
