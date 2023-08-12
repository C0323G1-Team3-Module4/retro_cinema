package com.example.retro_cinema.seats.repository;

import com.example.retro_cinema.seats.model.Seats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISeatsRepository extends JpaRepository<Seats,Integer> {
    Seats findBySeatName(String seatName);
}
