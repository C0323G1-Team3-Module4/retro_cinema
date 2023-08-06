package com.example.retro_cinema.screenings.service;

import com.example.retro_cinema.movie.model.Movie;
import com.example.retro_cinema.movie.repository.IMovieRepository;
import com.example.retro_cinema.screenings.dto.IScreeningsDto;
import com.example.retro_cinema.screenings.dto.ScreeningsDto;
import com.example.retro_cinema.screenings.model.Screenings;
import com.example.retro_cinema.screenings.repository.IScreeningsRepository;
import com.example.retro_cinema.showtimes.model.ShowTimes;
import com.example.retro_cinema.showtimes.repository.IShowTimesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ScreeningsService implements IScreeningsService {
    @Autowired
    private IScreeningsRepository screeningsRepository;
    @Autowired
    private IMovieRepository movieRepository;
    @Autowired
    private IShowTimesRepository showTimesRepository;

    @Override
    public List<IScreeningsDto> getAll() {
        return screeningsRepository.getAllScreenings();
    }

    @Override
    public void addScreenings(ScreeningsDto screeningsDto) {
        List<Movie> movieList = movieRepository.findAll();
        int idShowTime = Integer.parseInt(screeningsDto.getShowtimeId());
        ShowTimes showTimes = showTimesRepository.findById(idShowTime).get();
        int idMovie = 0;
        for (Movie m : movieList) {
            if (m.getMovieName().equals(screeningsDto.getMovieName())) {
                idMovie = m.getId();
            }
        }
        Movie movie = movieRepository.findById(idMovie).get();
        Screenings screenings = new Screenings(showTimes, movie, screeningsDto.getDateMovie());
        screeningsRepository.save(screenings);
    }

    @Override
    public List<IScreeningsDto> getAllByNameMovie(String nameMovie) {
        List<IScreeningsDto> screeningsDtoList = new ArrayList<>();
        List<IScreeningsDto> getAllListDto = getAll();
        for (IScreeningsDto i: getAllListDto) {
            if(i.getMovieName().equals(nameMovie)){
                screeningsDtoList.add(i);
            }
        }
        return screeningsDtoList;
    }
}
