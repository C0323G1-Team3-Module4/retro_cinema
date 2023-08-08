package com.example.retro_cinema.seatDetails.service;

import com.example.retro_cinema.seatDetails.model.SeatDetails;
import com.example.retro_cinema.seats.model.Seats;

import java.util.List;

public interface ISeatDetailsService {
    List<SeatDetails> getAllSeatsDetails();
}
