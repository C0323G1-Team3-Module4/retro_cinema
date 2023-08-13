package com.example.retro_cinema.seat_details.controller;

import com.example.retro_cinema.movie.service.IMovieService;
import com.example.retro_cinema.screenings.model.Screenings;
import com.example.retro_cinema.screenings.service.IScreeningsService;
import com.example.retro_cinema.seat_details.model.SeatDetails;
import com.example.retro_cinema.seat_details.service.ISeatDetailsService;
import com.example.retro_cinema.user.service.account.IAccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SeatsDetailsController {
    @Autowired
    private ISeatDetailsService seatDetailsService;
    @Autowired
    private IScreeningsService screeningsService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IMovieService movieService;
//    @GetMapping("/seatsDetail/list")
//    public String showList(Model model){
//        model.addAttribute("seatDetailsList",seatDetailsService.getAllSeatsDetails());
//        return "/seats_details/list";
//    }
    @PostMapping("/seatDetail")
    public String seatsDetail(@RequestParam("screeningId") int screeningId, @RequestParam("username") String username,Model model){
        model.addAttribute("userId",accountService.findByUsername(username).getId());
        model.addAttribute("seatDetail",seatDetailsService.getBySeatDetailsByIdScreenings(screeningId));
        Screenings screenings = screeningsService.getScreeningById(screeningId);
        List<Screenings> screeningsList = screeningsService.getAllByNameMovie(screenings.getMovie().getMovieName());
        ObjectMapper objectMapper = new ObjectMapper();
        String seatDetailJson = null;
        String screeningsJson = null;
        try {
            seatDetailJson = objectMapper.writeValueAsString(seatDetailsService.getBySeatDetailsByIdScreenings(screeningId));
            System.out.println(seatDetailJson);
            screeningsJson = objectMapper.writeValueAsString(screeningsList);
            model.addAttribute("seatDetailJson", seatDetailJson);
            model.addAttribute("screeningsJson", screeningsJson);
            System.out.println(screeningsJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "/screenings/screening";
    }
    @PostMapping("/showTickets")
    public String showTickets(@RequestParam("screeningId") int screeningId, @RequestParam("userId") int userId, Model model) {
        List<SeatDetails> tickets = seatDetailsService.findTicketsByUser(userId,screeningId,true);
        model.addAttribute("tickets", tickets);
        for (SeatDetails ticket: tickets) {
            seatDetailsService.setFlagToFalse(ticket);
        }
        return "/bookings/bookings";
    }
//    @PostMapping("/confirm")
//    public void confirmAndSetFlagToFalse(@RequestParam("screeningId") int screeningId, @RequestParam("userId") int userId) {
//        List<SeatDetails> tickets = seatDetailsService.findTicketsByUser(userId,screeningId,true);
//        for (SeatDetails ticket: tickets) {
//            seatDetailsService.setFlagToFalse(ticket);
//        }
//        System.out.println("Da doi flag");
//    }
    @GetMapping("/seat-details/bookings/{id}")
    public String displayGetAllSeatsDetail(@PathVariable int id, Model model){
        model.addAttribute("bookingsList",seatDetailsService.getAllSeatsDetailsByUser(id));
        return "/seats_details/list";
    }
}
