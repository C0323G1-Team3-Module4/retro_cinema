package com.example.retro_cinema.seat_details.service;

import com.example.retro_cinema.seat_details.model.SeatDetails;

import java.util.List;

public interface ISeatDetailsService {
    List<SeatDetails> getAllSeatsDetails();
    List<SeatDetails> getBySeatDetailsByIdScreenings(int id);
    void save(SeatDetails seatDetails);
    List<SeatDetails> findTicketsByUser(int userId, int screeningId, boolean flag);
    void setFlagToFalse(SeatDetails ticket);
    List<SeatDetails> getAllSeatsDetailsByUser(int userId);
}
