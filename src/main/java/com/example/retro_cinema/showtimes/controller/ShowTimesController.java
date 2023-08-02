package com.example.retro_cinema.showtimes.controller;

import com.example.retro_cinema.showtimes.service.IShowTimesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ShowTimesController {
    @Autowired
    private IShowTimesService showTimesService;

    @GetMapping("/showtime/list")
    public String showTimes(Model model) {
        model.addAttribute("showTimesList",showTimesService.getAllShowTime());
        return "/showtime/list";
    }
}
