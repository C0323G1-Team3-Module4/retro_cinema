package com.example.retro_cinema.seats.controller;

import com.example.retro_cinema.seats.service.ISeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SeatsController {
    @Autowired
    private ISeatsService seatsService;

    @GetMapping("/seats/list")
    public String showSeats(Model model) {
        model.addAttribute("seatsList",seatsService.getAllSeats());
        return "seats/list";
    }
}
