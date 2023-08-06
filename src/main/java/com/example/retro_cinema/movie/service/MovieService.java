package com.example.retro_cinema.movie.service;

import com.example.retro_cinema.movie.model.Movie;
import com.example.retro_cinema.movie.repository.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Movie> getAllMovie() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getUpcomingMovie() {
        List<Movie> movieList = getAllMovie();
        List<Movie> movies = new ArrayList<>();
        for (Movie m: movieList) {
            if(Period.between(LocalDate.parse(m.getReleaseDate()),LocalDate.now()).getDays()<0){
                movies.add(m);
            }
        }
        return movies;
    }

    @Override
    public List<Movie> getCurrentlyShowingMovies() {
        List<Movie> movieList = getAllMovie();
        List<Movie> movies = new ArrayList<>();
        for (Movie m: movieList) {
            if(Period.between(LocalDate.parse(m.getReleaseDate()),LocalDate.now()).getDays()>=0){
                movies.add(m);
            }
        }
        return movies;
    }

    @Override
    public List<Movie> getMovieByName(String name) {
        List<Movie> movieList = getAllMovie();
        List<Movie> movies = new ArrayList<>();
        for (Movie m: movieList) {
            if(m.getMovieName().contains(name)){
                movies.add(m);
            }
        }
        return movies;
    }
}
