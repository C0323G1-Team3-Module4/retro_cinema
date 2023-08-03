package com.example.retro_cinema.movie_types.repository;

import com.example.retro_cinema.movie_types.model.MovieTypes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovieTypesRepository extends JpaRepository<MovieTypes,Integer> {
}
