package com.example.retro_cinema.screenings.controller;

import com.example.retro_cinema.movie.model.Movie;
import com.example.retro_cinema.movie.service.IMovieService;
import com.example.retro_cinema.screenings.model.Screenings;
import com.example.retro_cinema.screenings.service.IScreeningsService;
import com.example.retro_cinema.showtimes.service.IShowTimesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ScreeningsController {
    @Autowired
    private IScreeningsService screeningsService;
    @Autowired
    private IMovieService movieService;
    @Autowired
    private IShowTimesService showTimesService;

    @GetMapping("/screenings/list")
    public String showList(Model model) {
        model.addAttribute("screenings", screeningsService.getAll());
        return "screenings/list";
    }

    @GetMapping("/screenings/showAdd")
    public String showAdd(Model model) {
        model.addAttribute("screeningDto", new Screenings());
        model.addAttribute("showTimesList", showTimesService.getAllShowTime());
        model.addAttribute("movieList",movieService.getAllMovie());
        return "/screenings/add";
    }

    @PostMapping("/screenings/add")
    public String add(@ModelAttribute Screenings screenings){
        screeningsService.addScreenings(screenings);
        return "redirect:/screenings/list";
    }

    @GetMapping("/screenings/listByNameMovie")
    public String showListByNameMovie(@RequestParam int id,Model model){
        Movie movie = movieService.getById(id);
        List<Screenings> screeningsList = screeningsService.getAllByNameMovie(movie.getMovieName());
        model.addAttribute("screeningsList",screeningsList);
        return "screenings/listNameMovie";
    }

    @PostMapping("/screenings/listByNameMovie")
    public String showScreeningsByDate(@RequestParam String date, Model model) {
        List<Screenings> screeningsList  = screeningsService.getAllByDateTime(date);
        model.addAttribute("screeningsLists",screeningsList);
        return "/screenings/listScreeningsByDate";
    }



}
