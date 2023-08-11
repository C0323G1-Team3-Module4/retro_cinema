package com.example.retro_cinema.screenings.controller;

import com.example.retro_cinema.movie.model.Movie;
import com.example.retro_cinema.movie.service.IMovieService;
import com.example.retro_cinema.screenings.dto.ScreeningsDto;
import com.example.retro_cinema.screenings.model.Screenings;
import com.example.retro_cinema.screenings.service.IScreeningsService;
import com.example.retro_cinema.showtimes.model.ShowTimes;
import com.example.retro_cinema.showtimes.service.IShowTimesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
    public String showList(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Screenings> screeningsPage = screeningsService.getAllPage(pageable, true);
        model.addAttribute("screenings", screeningsPage);
        return "screenings/menu";
    }

    @GetMapping("/screenings/showAdd")
    public String showAdd(Model model) {
        model.addAttribute("screeningsDto", new ScreeningsDto());
        model.addAttribute("showTimesList", showTimesService.getAllShowTime());
        model.addAttribute("movieList", movieService.getAllMovie());
        return "/screenings/add";
    }

    @PostMapping("/screenings/addScreenings")
    public String add(@Valid @ModelAttribute ScreeningsDto screeningsDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        Screenings screenings = new Screenings();
        new ScreeningsDto().validate(screeningsDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("movieList", movieService.getAllMovie());
            model.addAttribute("showTimesList", showTimesService.getAllShowTime());
            model.addAttribute("screeningsDto", screeningsDto);
            return "/screenings/add";
        }
        BeanUtils.copyProperties(screeningsDto, screenings);
        List<Screenings> screeningsList = screeningsService.getAll();
        for (Screenings s : screeningsList) {
            if (s.getDateMovie().equals(screenings.getDateMovie()) && (s.getShowTimes().getId()==screenings.getShowTimes().getId())) {
                model.addAttribute("movieList", movieService.getAllMovie());
                model.addAttribute("showTimesList", showTimesService.getAllShowTime());
                model.addAttribute("screeningsDto", screeningsDto);
                model.addAttribute("message", "There were screenings during that time");
                return "/screenings/add";
            }
        }
        screeningsService.addScreenings(screenings);
        redirectAttributes.addFlashAttribute("message","successfully add new");
        return "redirect:/screenings/list";
    }

    @GetMapping("/screenings/listByNameMovie")
    public String showListByNameMovie(@RequestParam int id, Model model) {
        Movie movie = movieService.getById(id);
        List<Screenings> screeningsList = screeningsService.getAllByNameMovie(movie.getMovieName());
        model.addAttribute("screeningsList", screeningsList);
        return "screenings/listNameMovie";
    }

    @PostMapping("/screenings/listByNameMovie")
    public String showScreeningsByDate(@RequestParam String date, Model model) {
        List<Screenings> screeningsList = screeningsService.getAllByDateTime(date);
        model.addAttribute("screeningsLists", screeningsList);
        return "/screenings/listScreeningsByDate";
    }

    @PostMapping("/screenings/delete")
    public String remove(@RequestParam int id,RedirectAttributes redirectAttributes){
        screeningsService.deleteScreenings(id);
        redirectAttributes.addFlashAttribute("message","successful delete");
        return "redirect:/screenings/list";
    }
}
