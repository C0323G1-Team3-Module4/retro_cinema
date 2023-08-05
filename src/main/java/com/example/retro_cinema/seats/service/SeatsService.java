package com.example.retro_cinema.seats.service;

import com.example.retro_cinema.seats.model.Seats;
import com.example.retro_cinema.seats.repository.ISeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SeatsService implements ISeatsService{
    @Autowired
    private ISeatsRepository seatsRepository;
    @Override
    public List<Seats> getAllSeats() {
        return seatsRepository.findAll();
    }
}
