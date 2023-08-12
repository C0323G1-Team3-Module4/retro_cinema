package com.example.retro_cinema.seatDetails.controller;

import com.example.retro_cinema.screenings.model.Screenings;
import com.example.retro_cinema.screenings.service.IScreeningsService;
import com.example.retro_cinema.seatDetails.dto.SeatDetailDto;
import com.example.retro_cinema.seatDetails.model.SeatDetails;
import com.example.retro_cinema.seatDetails.service.ISeatDetailsService;
import com.example.retro_cinema.seats.model.Seats;
import com.example.retro_cinema.seats.service.ISeatsService;
import com.example.retro_cinema.user.model.AccountUser;
import com.example.retro_cinema.user.service.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SeatsDetailsRestController {
    @Autowired
    private ISeatDetailsService seatDetailsService;
    @Autowired
    private IScreeningsService screeningsService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private ISeatsService seatsService;

    @PostMapping("/save-seat-details")
    public ResponseEntity<String> saveSeatDetails(@RequestBody List<SeatDetailDto> seatDetails) {
        for (SeatDetailDto seatDetail : seatDetails) {
            AccountUser accountUser = accountService.findByIdAccount(seatDetail.getIdAccount());
            Seats seat = seatsService.findByName(seatDetail.getNameSeats());
            Screenings screening = screeningsService.getScreeningById(seatDetail.getIdScreenings());
            boolean flag = seatDetail.isFlag();
            SeatDetails newSeatDetail = new SeatDetails(flag, accountUser, screening, seat);
            seatDetailsService.save(newSeatDetail);
        }
        String message = "You had a successful purchase";
        return ResponseEntity.ok(message);
    }
}
