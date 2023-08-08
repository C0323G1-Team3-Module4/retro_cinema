package com.example.retro_cinema.movie.repository;

import com.example.retro_cinema.movie.model.Movie;
import com.example.retro_cinema.movie_types.model.MovieTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMovieRepository extends JpaRepository<Movie, Integer> {
    Page<Movie> findMovieByMovieNameContainingAndFlag(Pageable pageable, String name,boolean flag);

}
