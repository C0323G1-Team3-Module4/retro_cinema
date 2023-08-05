package com.example.retro_cinema.screenings.model;

import com.example.retro_cinema.movie.model.Movie;
import com.example.retro_cinema.showtimes.model.ShowTimes;

import javax.persistence.*;

@Entity
@Table(name = "screenings")
public class Screenings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "bit default 1")
    private boolean flag;
    @ManyToOne
    @JoinColumn(name = "showtime_id",referencedColumnName = "id")
    private ShowTimes showTimes;
    @ManyToOne
    @JoinColumn(name ="movie_id", referencedColumnName = "id")
    private Movie movie;
}
