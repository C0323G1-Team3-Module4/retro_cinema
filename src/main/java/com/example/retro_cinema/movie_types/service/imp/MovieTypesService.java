package com.example.retro_cinema.movie_types.service.imp;

import com.example.retro_cinema.movie_types.model.MovieTypes;
import com.example.retro_cinema.movie_types.repository.IMovieTypesRepository;
import com.example.retro_cinema.movie_types.service.IMovieTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieTypesService implements IMovieTypesService {
    @Autowired
    IMovieTypesRepository movieTypesRepository;
    @Override
    public List<MovieTypes> findAllMovieTypes() {
        return movieTypesRepository.findAll();
    }
}
