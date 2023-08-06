package com.example.retro_cinema.screenings.service;

import com.example.retro_cinema.screenings.dto.ScreeningsDto;

import java.util.List;

public interface IScreeningsService {
    List<ScreeningsDto> getAll();
}
