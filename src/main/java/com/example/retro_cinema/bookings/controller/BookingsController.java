package com.example.retro_cinema.bookings.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bookings")
public class BookingsController {
    @GetMapping("")
    public String showList() {
        return "/bookings/list";
    }

}
