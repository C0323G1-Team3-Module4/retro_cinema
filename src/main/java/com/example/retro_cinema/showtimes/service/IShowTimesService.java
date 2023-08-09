package com.example.retro_cinema.showtimes.service;

import com.example.retro_cinema.showtimes.dto.ShowTimesDto;
import com.example.retro_cinema.showtimes.model.ShowTimes;

import java.util.List;

public interface IShowTimesService {
    List<ShowTimesDto> getAllShowTime();
    ShowTimes getShowTimeById(int id);
}
