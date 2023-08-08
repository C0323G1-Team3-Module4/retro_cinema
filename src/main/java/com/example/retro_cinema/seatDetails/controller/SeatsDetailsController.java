package com.example.retro_cinema.seatDetails.controller;

import com.example.retro_cinema.seatDetails.service.ISeatDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SeatsDetailsController {
    @Autowired
    private ISeatDetailsService seatDetailsService;
    @GetMapping("/seatsDetail/list")
    public String showList(Model model){
        model.addAttribute("seatDetailsList",seatDetailsService.getAllSeatsDetails());
        return "/seats_details/list";
    }
}
