package com.example.retro_cinema.movie.service;

import com.example.retro_cinema.movie.model.Movie;
import com.example.retro_cinema.movie_types.model.MovieTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMovieService {

    Page<Movie> movieList(Pageable pageable, String name, boolean flag);


    void addMovie(Movie movie);

    void deleteMovie(int id);

    Movie getById(int id);

    void editMovie(Movie movie);

    List<Movie> getAllMovie();

    List<Movie> getUpcomingMovie();

    List<Movie> getCurrentlyShowingMovies();
    List<Movie> getUpcomingMovieByName(String name);
    List<Movie> getCurrentlyShowingMoviesByName(String name);
}
