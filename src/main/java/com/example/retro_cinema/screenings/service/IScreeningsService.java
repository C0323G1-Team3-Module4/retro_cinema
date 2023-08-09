package com.example.retro_cinema.screenings.service;


import com.example.retro_cinema.screenings.model.Screenings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface IScreeningsService {
    List<Screenings> getAll();
    void addScreenings(Screenings screenings);
    List<Screenings> getAllByNameMovie(String nameMovie);
    List<Screenings> getAllByDateTime(String dateTime);
    Set<String> dateList(String name);
    Page<Screenings> getAllPage(Pageable pageable);

}
