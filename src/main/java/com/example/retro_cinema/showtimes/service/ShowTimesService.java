package com.example.retro_cinema.showtimes.service;

import com.example.retro_cinema.showtimes.dto.ShowTimesDto;
import com.example.retro_cinema.showtimes.model.ShowTimes;
import com.example.retro_cinema.showtimes.repository.IShowTimesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowTimesService implements IShowTimesService {
    @Autowired
    private IShowTimesRepository showTimesRepository;
    @Override
    public List<ShowTimesDto> getAllShowTime() {
        return showTimesRepository.getShowTimeDto();
    }

    @Override
    public ShowTimes getShowTimeById(int id) {
        return showTimesRepository.findById(id).get();
    }
}
