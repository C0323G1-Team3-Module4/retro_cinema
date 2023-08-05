package com.example.retro_cinema.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountUserController {
    @GetMapping("/preference")
    public String toPreference() {
        return "admin_preferences";
    }
}
