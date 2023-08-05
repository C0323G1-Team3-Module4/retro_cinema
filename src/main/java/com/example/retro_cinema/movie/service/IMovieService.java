package com.example.retro_cinema.movie.service;

import com.example.retro_cinema.movie.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMovieService {

    Page<Movie> movieList(Pageable pageable, String name);


    void addMovie(Movie movie);

    void deleteMovie(int id);

    Movie getById(int id);

    void editMovie(Movie movie);
}
