package com.example.retro_cinema.movie.controller;

import com.example.retro_cinema.customer.dto.CustomerDto;
import com.example.retro_cinema.customer.model.Customer;
import com.example.retro_cinema.movie.dto.MovieDto;
import com.example.retro_cinema.movie.model.Movie;
import com.example.retro_cinema.movie.service.IMovieService;
import com.example.retro_cinema.movie_types.model.MovieTypes;
import com.example.retro_cinema.movie_types.service.IMovieTypesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    IMovieService movieService;
    @Autowired
    IMovieTypesService movieTypesService;

    @GetMapping
    public ModelAndView showList(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "") String name) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("releaseDate").descending());
        Page<Movie> moviePage = movieService.movieList(pageable, name);
        ModelAndView modelAndView = new ModelAndView("movie/list");
        modelAndView.addObject("moviePage", moviePage);
        modelAndView.addObject("name", name);
        return modelAndView;
    }

    @GetMapping("/create")
    public String createMovie(Model model) {
        model.addAttribute("movieDto", new MovieDto());
        model.addAttribute("movieTypes", movieTypesService.findAllMovieTypes());
        return "/movie/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute MovieDto movieDto,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        Movie movie = new Movie();
        new MovieDto().validate(movieDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("movieTypes", movieTypesService.findAllMovieTypes());
            model.addAttribute("movieDto", movieDto);
            return "/movie/create";
        }
        BeanUtils.copyProperties(movieDto, movie);
        movieService.addMovie(movie);
        redirectAttributes.addFlashAttribute("msg", "Successfully added new");
        return "redirect:/movie";
    }

    @GetMapping("/edit")
    public String editMovie(@RequestParam int id, Model model) {
        Movie movie = movieService.getById(id);
        MovieDto movieDto = new MovieDto();
        BeanUtils.copyProperties(movie, movieDto);
        model.addAttribute("movieTypesList", movieTypesService.findAllMovieTypes());
        model.addAttribute("movieDto", movieDto);
        return "/movie/edit";
    }

    @PostMapping("/edit")
    public String update(@Valid @ModelAttribute MovieDto movieDto, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, Model model) {
        Movie movie = new Movie();
        new MovieDto().validate(movieDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("movieTypesList", movieTypesService.findAllMovieTypes());
            model.addAttribute("movieDto", movieDto);
            return "/movie/edit";
        }
        BeanUtils.copyProperties(movieDto, movie);
        movieService.editMovie(movie);
        redirectAttributes.addFlashAttribute("msg", "Update movie Success!");
        return "redirect:/movie";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id, RedirectAttributes redirectAttributes) {
        movieService.deleteMovie(id);
        redirectAttributes.addFlashAttribute("msg", "Delete successfully");
        return "redirect:/movie";
    }
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable int id, Model model) {
        model.addAttribute("movie", movieService.getById(id));
        return "/movie/detail";
    }
    @GetMapping("/search/{name}")
    public String search(@PathVariable String name,Model model){
        model.addAttribute("findMovieByName",movieService.getMovieByName(name));
        return "/movie/search";
    }
}
