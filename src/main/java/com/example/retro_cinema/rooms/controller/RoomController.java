package com.example.retro_cinema.rooms.controller;

import com.example.retro_cinema.rooms.service.IRoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/rooms")
@Controller
public class RoomController {
    @Autowired
    private IRoomsService roomsService;
    @GetMapping("")
    public String showList(Model model) {
        model.addAttribute("roomsList",roomsService.findAll());
        return "/rooms/list";
    }
}
