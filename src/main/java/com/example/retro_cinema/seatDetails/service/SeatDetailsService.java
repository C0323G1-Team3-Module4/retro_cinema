package com.example.retro_cinema.seatDetails.service;

import com.example.retro_cinema.seatDetails.model.SeatDetails;
import com.example.retro_cinema.seatDetails.repository.ISeatDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SeatDetailsService implements ISeatDetailsService{
    @Autowired
    private ISeatDetailsRepository seatDetailsRepository;
    @Override
    public List<SeatDetails> getAllSeatsDetails() {
        return seatDetailsRepository.findAll();
    }
}
