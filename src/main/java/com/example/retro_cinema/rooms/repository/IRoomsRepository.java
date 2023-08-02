package com.example.retro_cinema.rooms.repository;

import com.example.retro_cinema.rooms.model.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoomsRepository extends JpaRepository<Rooms,Integer> {
}
