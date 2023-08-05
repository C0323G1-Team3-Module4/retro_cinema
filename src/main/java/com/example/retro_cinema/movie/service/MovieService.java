package com.example.retro_cinema.movie.service;

import com.example.retro_cinema.movie.model.Movie;
import com.example.retro_cinema.movie.repository.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MovieService implements IMovieService {
    @Autowired
    IMovieRepository movieRepository;

    @Override
    public Page<Movie> movieList(Pageable pageable, String name) {
        return movieRepository.findMovieByMovieNameContaining(pageable, name);
    }


    @Override
    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(int id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Movie getById(int id) {
        return movieRepository.findById(id).get();
    }

    @Override
    public void editMovie(Movie movie) {
        movieRepository.save(movie);
    }
}
