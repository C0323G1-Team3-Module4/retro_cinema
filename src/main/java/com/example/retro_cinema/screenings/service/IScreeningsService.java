package com.example.retro_cinema.screenings.service;

import com.example.retro_cinema.screenings.dto.IScreeningsDto;
import com.example.retro_cinema.screenings.dto.ScreeningsDto;

import java.util.List;

public interface IScreeningsService {
    List<IScreeningsDto> getAll();
    void addScreenings(ScreeningsDto screeningsDto);
    List<IScreeningsDto> getAllByNameMovie(String nameMovie);
    List<IScreeningsDto> getAllByDateTime(String dateTime);
}
