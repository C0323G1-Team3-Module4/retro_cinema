package com.example.retro_cinema.movie_types.model;

import com.example.retro_cinema.movie.model.Movie;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "movie_types")
public class MovieTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "movie_type")
    private String movieTypes;
    @OneToMany(mappedBy = "movieTypes")
    @JsonBackReference
    private Set<Movie> movieSet;

    public Set<Movie> getMovieSet() {
        return movieSet;
    }

    public void setMovieSet(Set<Movie> movieSet) {
        this.movieSet = movieSet;
    }

    public MovieTypes() {
    }

    public MovieTypes(int id, String movieTypes) {
        this.id = id;
        this.movieTypes = movieTypes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieTypes() {
        return movieTypes;
    }

    public void setMovieTypes(String movieTypes) {
        this.movieTypes = movieTypes;
    }
}
