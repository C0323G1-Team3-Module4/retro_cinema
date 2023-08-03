package com.example.retro_cinema.movie.repository;

import com.example.retro_cinema.movie.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovieRepository extends JpaRepository<Movie, Integer> {
    Page<Movie> findMovieByMovieNameContaining(Pageable pageable, String name);
}
