package com.example.retro_cinema.payment.controller;

import com.example.retro_cinema.customer.service.ICustomerService;
import com.example.retro_cinema.screenings.service.IScreeningsService;
import com.example.retro_cinema.seats.model.Seats;
import com.example.retro_cinema.seats.service.ISeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequestMapping("payment")
public class PaymentController {
    @Autowired
    private ICustomerService iCustomerService;
    @Autowired
    private ISeatsService iSeatsService;
    @Autowired
    private IScreeningsService iScreeningsService;

    @PostMapping("/create")
    public String paymentCreate(@RequestParam int quantity, @RequestParam int total, @RequestParam Integer idCustomer,
                                @SessionAttribute List<Seats> seatsList) throws UnsupportedOperationException {
        return null;
    }
}
