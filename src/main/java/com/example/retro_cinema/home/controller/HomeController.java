package com.example.retro_cinema.home.controller;

import com.example.retro_cinema.movie.model.Movie;
import com.example.retro_cinema.movie.service.IMovieService;
import com.example.retro_cinema.screenings.model.Screenings;
import com.example.retro_cinema.screenings.service.IScreeningsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private IMovieService movieService;
    @Autowired
    private IScreeningsService screeningsService;

    @GetMapping("/")
    public String showHomePage(@RequestParam(defaultValue = "") String name, Model model) {
        model.addAttribute("upcomingMovie", movieService.getUpcomingMovieByName(name));
        model.addAttribute("currentlyShowingMovies", movieService.getCurrentlyShowingMoviesByName(name));
        return "home";
    }

    @GetMapping("/home/play_page/{id}")
    public String playPage(@PathVariable int id, Model model) {
        Movie movie = movieService.getById(id);
        model.addAttribute("movie", movie);
        return "/play_page";
    }
    @GetMapping("/home/screening/{id}")
    public String showScreening(@PathVariable("id") int id, Model model) {
        Movie movie = movieService.getById(id);
        List<Screenings> screeningsList = screeningsService.getAllByNameMovie(movie.getMovieName());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String screeningsJson = objectMapper.writeValueAsString(screeningsList);
            System.out.println(screeningsJson);
            model.addAttribute("screeningsJson",screeningsJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        model.addAttribute("screeningList",screeningsList);

        return "/screenings/screening";
    }
}
