package com.example.retro_cinema.home.controller;

import com.example.retro_cinema.movie.model.Movie;
import com.example.retro_cinema.movie.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private IMovieService movieService;

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("movieList", movieService.getAllMovie());
        model.addAttribute("upcomingMovie", movieService.getUpcomingMovie());
        model.addAttribute("currentlyShowingMovies", movieService.getCurrentlyShowingMovies());
        return "home";
    }

    @GetMapping("/home/play_page/{id}")
    public String playPage(@PathVariable int id, Model model) {
        Movie movie = movieService.getById(id);
        model.addAttribute("movie", movie);
        return "/play_page";
    }
}
