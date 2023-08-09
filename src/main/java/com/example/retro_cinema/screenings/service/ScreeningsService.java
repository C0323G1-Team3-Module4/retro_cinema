package com.example.retro_cinema.screenings.service;

import com.example.retro_cinema.screenings.model.Screenings;
import com.example.retro_cinema.screenings.repository.IScreeningsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class ScreeningsService implements IScreeningsService {
    @Autowired
    private IScreeningsRepository screeningsRepository;

    @Override
    public List<Screenings> getAll() {
        return screeningsRepository.findAll();
    }

    @Override
    public void addScreenings(Screenings screenings) {
        screeningsRepository.save(screenings);
    }

    @Override
    public List<Screenings> getAllByNameMovie(String nameMovie) {
        List<Screenings> screeningsList = new ArrayList<>();
        List<Screenings> getAllListDto = screeningsRepository.findAll();
        for (Screenings i: getAllListDto) {
            if(i.getMovie().getMovieName().equals(nameMovie)){
                screeningsList.add(i);
            }
        }
        return screeningsList;
    }

    @Override
    public List<Screenings> getAllByDateTime(String dateTime) {
        List<Screenings> screeningsDtoListByDate = new ArrayList<>();
        List<Screenings> getAllListDto = getAll();
        for (Screenings i: getAllListDto) {
            if(i.getDateMovie().equals(dateTime)){
                screeningsDtoListByDate.add(i);
            }
        }
        return screeningsDtoListByDate;
    }

    @Override
    public Set<String> dateList(String name) {
        List<Screenings> listName = getAllByNameMovie(name);
        Set<String> stringList = new HashSet<>();
        for (Screenings s: listName) {
            stringList.add(s.getDateMovie());
        }
        return stringList;
    }

    @Override
    public Page<Screenings> getAllPage(Pageable pageable) {
        return screeningsRepository.findAll(pageable);
    }
}
