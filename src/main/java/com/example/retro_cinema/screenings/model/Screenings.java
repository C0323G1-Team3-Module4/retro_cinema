package com.example.retro_cinema.screenings.model;

import com.example.retro_cinema.movie.model.Movie;
import com.example.retro_cinema.product_detail.model.ProductDetail;
import com.example.retro_cinema.seatDetails.model.SeatDetails;
import com.example.retro_cinema.showtimes.model.ShowTimes;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

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
    @Column(name = "date_movie", columnDefinition = "DATE")
    private String dateMovie;
    @OneToMany(mappedBy = "screenings")
    @JsonBackReference
    private Set<SeatDetails> seatDetails;
    @OneToMany(mappedBy = "screenings")
    @JsonBackReference
    private Set<ProductDetail> productDetails;

    public Screenings() {
    }

    public Screenings(int id, boolean flag, ShowTimes showTimes, Movie movie, String dateMovie) {
        this.id = id;
        this.flag = flag;
        this.showTimes = showTimes;
        this.movie = movie;
        this.dateMovie = dateMovie;
    }

    public Screenings(boolean flag, ShowTimes showTimes, Movie movie, String dateMovie) {
        this.flag = flag;
        this.showTimes = showTimes;
        this.movie = movie;
        this.dateMovie = dateMovie;
    }

    public Screenings(ShowTimes showTimes, Movie movie, String dateMovie) {
        this.showTimes = showTimes;
        this.movie = movie;
        this.dateMovie = dateMovie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public ShowTimes getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(ShowTimes showTimes) {
        this.showTimes = showTimes;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getDateMovie() {
        return dateMovie;
    }

    public void setDateMovie(String dateMovie) {
        this.dateMovie = dateMovie;
    }

    public Set<SeatDetails> getSeatDetails() {
        return seatDetails;
    }

    public void setSeatDetails(Set<SeatDetails> seatDetails) {
        this.seatDetails = seatDetails;
    }
}
