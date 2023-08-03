package com.example.retro_cinema.movie_types.model;

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
    Set<MovieTypes> movieTypesSet;

    public Set<MovieTypes> getMovieTypesSet() {
        return movieTypesSet;
    }

    public void setMovieTypesSet(Set<MovieTypes> movieTypesSet) {
        this.movieTypesSet = movieTypesSet;
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
