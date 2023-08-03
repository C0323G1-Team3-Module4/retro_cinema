package com.example.retro_cinema.movie_types.controller;

import com.example.retro_cinema.movie_types.model.MovieTypes;
import com.example.retro_cinema.movie_types.service.IMovieTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/movieTypes")
public class MovieTypesController {
    @Autowired
    IMovieTypesService movieTypesService;
    @GetMapping
    public String showMovieTypes(Model model){
        List<MovieTypes> movieTypesList = movieTypesService.findAllMovieTypes();
        model.addAttribute("list", movieTypesList);
        return "/movie_types/list";
    }
}
