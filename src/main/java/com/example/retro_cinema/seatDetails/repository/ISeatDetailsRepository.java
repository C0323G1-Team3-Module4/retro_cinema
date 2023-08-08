package com.example.retro_cinema.seatDetails.repository;

import com.example.retro_cinema.seatDetails.model.SeatDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISeatDetailsRepository extends JpaRepository<SeatDetails,Integer> {
}
