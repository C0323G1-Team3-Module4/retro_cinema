package com.example.retro_cinema.seatDetails.controller;

import com.example.retro_cinema.seatDetails.service.ISeatDetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SeatsDetailsController {
    @Autowired
    private ISeatDetailsService seatDetailsService;
    @GetMapping("/seatsDetail/list")
    public String showList(Model model){
        model.addAttribute("seatDetailsList",seatDetailsService.getAllSeatsDetails());
        return "/seats_details/list";
    }
    @GetMapping("/seatDetail/{screeningId}")
    public String seatsDetail(@PathVariable int screeningId, Model model){
        model.addAttribute("seatDetail",seatDetailsService.getBySeatDetailsByIdScreenings(screeningId));
        ObjectMapper objectMapper = new ObjectMapper();
        String seatDetailJson = null;
        try {
            seatDetailJson = objectMapper.writeValueAsString(seatDetailsService.getBySeatDetailsByIdScreenings(screeningId));
            System.out.println(seatDetailJson);
            model.addAttribute("seatDetailJson", seatDetailJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "/screenings/screening";
    }


}
