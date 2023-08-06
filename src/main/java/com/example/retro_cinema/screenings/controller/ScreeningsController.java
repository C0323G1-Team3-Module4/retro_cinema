package com.example.retro_cinema.screenings.controller;

import com.example.retro_cinema.screenings.service.IScreeningsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScreeningsController {
    @Autowired
    private IScreeningsService screeningsService;

    @GetMapping("/screenings/list")
    public String showList(Model model) {
        model.addAttribute("screeningDto",screeningsService.getAll());
        return "screenings/list";
    }
}
