package com.example.retro_cinema.movie.model;

import com.example.retro_cinema.movie_types.model.MovieTypes;
import com.example.retro_cinema.screenings.model.Screenings;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "movie_name")
    private String movieName;
    private int duration;
    private String description;
    @Column(name = "release_date")
    private String releaseDate;
    private String director;
    private String performer;
    private String img;
    private String imgTrailer;
    private String trailer;
    private double price;
    private boolean flag;
    @ManyToOne
    @JoinColumn(name = "movie_type_id",referencedColumnName = "id")
    private MovieTypes movieTypes;

    @OneToMany(mappedBy = "movie")
    private Set<Screenings> screeningsSet;
    public Movie() {
    }

    public Set<Screenings> getScreeningsSet() {
        return screeningsSet;
    }

    public void setScreeningsSet(Set<Screenings> screeningsSet) {
        this.screeningsSet = screeningsSet;
    }

    public Movie(int id, String movieName, int duration, String description, String releaseDate, String director, String performer, String img, String trailer, double price, boolean flag, MovieTypes movieTypes) {
        this.id = id;
        this.movieName = movieName;
        this.duration = duration;
        this.description = description;
        this.releaseDate = releaseDate;
        this.director = director;
        this.performer = performer;
        this.img = img;
        this.trailer = trailer;
        this.price = price;
        this.flag = flag;
        this.movieTypes = movieTypes;
    }

    public Movie(String movieName, int duration, String description, String releaseDate, String director, String performer, String img, String trailer, double price, boolean flag, MovieTypes movieTypes) {
        this.movieName = movieName;
        this.duration = duration;
        this.description = description;
        this.releaseDate = releaseDate;
        this.director = director;
        this.performer = performer;
        this.img = img;
        this.trailer = trailer;
        this.price = price;
        this.flag = flag;
        this.movieTypes = movieTypes;
    }

    public Movie(int id, String movieName, int duration, String description, String releaseDate, String director, String performer, String img, String imgTrailer, String trailer, double price, boolean flag, MovieTypes movieTypes, Set<Screenings> screeningsSet) {
        this.id = id;
        this.movieName = movieName;
        this.duration = duration;
        this.description = description;
        this.releaseDate = releaseDate;
        this.director = director;
        this.performer = performer;
        this.img = img;
        this.imgTrailer = imgTrailer;
        this.trailer = trailer;
        this.price = price;
        this.flag = flag;
        this.movieTypes = movieTypes;
        this.screeningsSet = screeningsSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isFlag() {
        return flag;
    }

    public String getImgTrailer() {
        return imgTrailer;
    }

    public void setImgTrailer(String imgTrailer) {
        this.imgTrailer = imgTrailer;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public MovieTypes getMovieTypes() {
        return movieTypes;
    }

    public void setMovieTypes(MovieTypes movieTypes) {
        this.movieTypes = movieTypes;
    }

}
