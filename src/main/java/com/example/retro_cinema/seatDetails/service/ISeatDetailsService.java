package com.example.retro_cinema.seatDetails.service;

import com.example.retro_cinema.seatDetails.model.SeatDetails;

import java.util.List;

public interface ISeatDetailsService {
    List<SeatDetails> getAllSeatsDetails();
    List<SeatDetails> getBySeatDetailsByIdScreenings(int id);
    void save(SeatDetails seatDetails);
    List<SeatDetails> findTicketsByUser(int userId, int screeningId, boolean flag);
    void setFlagToFalse(SeatDetails ticket);
}
