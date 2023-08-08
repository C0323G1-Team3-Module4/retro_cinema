package com.example.retro_cinema.movie.service;

import com.example.retro_cinema.movie.model.Movie;
import com.example.retro_cinema.movie.repository.IMovieRepository;
import com.example.retro_cinema.movie_types.model.MovieTypes;
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
    public Page<Movie> movieList(Pageable pageable, String name, boolean flag) {
        return movieRepository.findMovieByMovieNameContainingAndFlag(pageable, name, true);
    }


    @Override
    public void addMovie(Movie movie) {
        movie.setFlag(true);
        movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(int id) {
        Movie movie = getById(id);
        movie.setFlag(false);
        movieRepository.save(movie);
    }

    @Override
    public Movie getById(int id) {
        return movieRepository.findById(id).get();
    }

    @Override
    public void editMovie(Movie movie) {
        movie.setFlag(true);
        movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovie() {
        List<Movie> movies = new ArrayList<>();
        List<Movie> movieList = movieRepository.findAll();
        for (Movie m: movieList) {
            if(m.isFlag()){
                movies.add(m);
            }
        }
        return movies;
    }

    @Override
    public List<Movie> getUpcomingMovie() {
        List<Movie> movieList = getAllMovie();
        List<Movie> movies = new ArrayList<>();
        for (Movie m : movieList) {
            if (Period.between(LocalDate.parse(m.getReleaseDate()), LocalDate.now()).getDays() < 0) {
                movies.add(m);
            }
        }
        return movies;
    }

    @Override
    public List<Movie> getCurrentlyShowingMovies() {
        List<Movie> movieList = getAllMovie();
        List<Movie> movies = new ArrayList<>();
        for (Movie m : movieList) {
            if (Period.between(LocalDate.parse(m.getReleaseDate()), LocalDate.now()).getDays() >= 0) {
                movies.add(m);
            }
        }
        return movies;
    }

    @Override
    public List<Movie> getUpcomingMovieByName(String name) {
        List<Movie> movieList = getUpcomingMovie();
        List<Movie> movies = new ArrayList<>();
        if (name == null) {
            movies = getUpcomingMovie();
        } else {
            for (Movie m : movieList) {
                if (m.getMovieName().contains(name)) {
                    movies.add(m);
                }
            }
        }
        return movies;
    }

    @Override
    public List<Movie> getCurrentlyShowingMoviesByName(String name) {
        List<Movie> movieList = getCurrentlyShowingMovies();
        List<Movie> movies = new ArrayList<>();
        if (name == null) {
            movies = getCurrentlyShowingMovies();
        } else {
            for (Movie m : movieList) {
                if (m.getMovieName().contains(name)) {
                    movies.add(m);
                }
            }
        }
        return movies;
    }
}
