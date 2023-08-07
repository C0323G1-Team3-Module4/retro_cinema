package com.example.retro_cinema.movie.dto;

import com.example.retro_cinema.movie_types.model.MovieTypes;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.Period;

public class MovieDto implements Validator {
    private int id;
    private String movieName;
    @Min(value = 1, message = "Movie show time must be greater than 0")
    @Max(value = 120, message = "The time cannot be longer than 2 hours")
    private int duration;
    @NotBlank(message = "Description cannot is empty")
    private String description;
    private String releaseDate;
    private String director;
    private String performer;
//    @NotBlank(message = "Link img cannot is empty")
    private String img;
    private String imgTrailer;
    @NotBlank(message = "Link trailer cannot is empty")
    private String trailer;
    @Min(value = 1, message = "Price must be greater than 0")
    private double price;
    private boolean flag;
    private MovieTypes movieTypes;

    public MovieDto() {
    }

    public MovieDto(int id, String movieName, int duration, String description, String releaseDate, String director, String performer, String img, String trailer, double price, boolean flag, MovieTypes movieTypes) {
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

    public MovieDto(String movieName, int duration, String description, String releaseDate, String director, String performer, String img, String trailer, double price, boolean flag, MovieTypes movieTypes) {
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

    public MovieDto(int id, String movieName, int duration, String description, String releaseDate, String director, String performer, String img, String imgTrailer, String trailer, double price, boolean flag, MovieTypes movieTypes) {
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
    }

    public MovieDto(String movieName, int duration, String description, String releaseDate, String director, String performer, String img, String imgTrailer, String trailer, double price, boolean flag, MovieTypes movieTypes) {
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

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public MovieTypes getMovieTypes() {
        return movieTypes;
    }

    public String getImgTrailer() {
        return imgTrailer;
    }

    public void setImgTrailer(String imgTrailer) {
        this.imgTrailer = imgTrailer;
    }

    public void setMovieTypes(MovieTypes movieTypes) {
        this.movieTypes = movieTypes;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        MovieDto movieDto = (MovieDto) target;
        if (movieDto.getMovieName().equals("")) {
            errors.rejectValue("movieName", "", "Movie name cannot is empty!");
        } else if (!movieDto.getMovieName().matches("^[\\p{Lu}][\\p{Ll}]*([\\s][\\p{Lu}][\\p{Ll}]*)*$")) {
            errors.rejectValue("movieName", "", "Invalid movie name!");
        }
        try{
            LocalDate localDate = LocalDate.parse(movieDto.getReleaseDate());
            LocalDate localNow = LocalDate.now();
            if (Period.between(localDate, localNow).getDays() > 0) {
                errors.rejectValue("releaseDate", "", "The time cannot be earlier than the current date!");
            }
        }catch (Exception e){
            errors.rejectValue("releaseDate", "", "ReleaseDate cannot is empty!");
        }

        if (movieDto.getDirector().equals("")) {
            errors.rejectValue("director", "", "Director's name cannot is empty!");
        } else if (!movieDto.getDirector().matches("^[\\p{Lu}][\\p{Ll}]*([\\s][\\p{Lu}][\\p{Ll}]*)*$")) {
            errors.rejectValue("director", "", "Invalid Director's name!");
        }
        if (movieDto.getPerformer().equals("")) {
            errors.rejectValue("performer", "", "Performer's name cannot is empty!");
        } else if (!movieDto.getPerformer().matches("^[\\p{Lu}][\\p{Ll}]*([\\s][\\p{Lu}][\\p{Ll}]*)*$")) {
            errors.rejectValue("performer", "", "Invalid Performer's name!");
        }
        try{
            if (!movieDto.getMovieTypes().getMovieTypes().matches("^[\\p{Lu}][\\p{Ll}]*([\\s][\\p{Lu}][\\p{Ll}]*)*$")) {
                errors.rejectValue("movieTypes", "", "Invalid Performer's name!");
            }
        }catch (NullPointerException e){
            errors.rejectValue("movieTypes", "", "Performer's name cannot is empty!");
        }

    }

}
