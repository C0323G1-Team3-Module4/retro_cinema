package com.example.retro_cinema.movie_types.service;

import com.example.retro_cinema.movie_types.model.MovieTypes;

import java.util.List;

public interface IMovieTypesService {
    List<MovieTypes> findAllMovieTypes();
}
