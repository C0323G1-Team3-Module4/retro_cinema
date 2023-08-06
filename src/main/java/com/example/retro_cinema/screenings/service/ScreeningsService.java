package com.example.retro_cinema.screenings.service;

import com.example.retro_cinema.screenings.dto.ScreeningsDto;
import com.example.retro_cinema.screenings.repository.IScreeningsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ScreeningsService implements IScreeningsService{
    @Autowired
    private IScreeningsRepository screeningsRepository;
    @Override
    public List<ScreeningsDto> getAll() {
        return screeningsRepository.getAllScreenings();
    }
}
