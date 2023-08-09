package com.example.retro_cinema.rooms.service;

import com.example.retro_cinema.rooms.model.Rooms;
import com.example.retro_cinema.rooms.repository.IRoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService implements IRoomsService {
    @Autowired
    private IRoomsRepository roomsRepository;

    @Override
    public List<Rooms> findAll() {
        return roomsRepository.findAll();
    }
}
