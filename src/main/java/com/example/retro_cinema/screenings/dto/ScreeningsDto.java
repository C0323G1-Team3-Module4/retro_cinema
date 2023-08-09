package com.example.retro_cinema.screenings.dto;

import com.example.retro_cinema.movie.model.Movie;
import com.example.retro_cinema.product_detail.model.ProductDetail;
import com.example.retro_cinema.seatDetails.model.SeatDetails;
import com.example.retro_cinema.showtimes.model.ShowTimes;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

public class ScreeningsDto implements Validator {
    private int id;
    private boolean flag;
    private ShowTimes showTimes;
    private Movie movie;
    private String dateMovie;
    private Set<SeatDetails> seatDetails;
    private Set<ProductDetail> productDetails;

    public ScreeningsDto() {
    }

    public ScreeningsDto(int id, boolean flag, ShowTimes showTimes, Movie movie, String dateMovie, Set<SeatDetails> seatDetails, Set<ProductDetail> productDetails) {
        this.id = id;
        this.flag = flag;
        this.showTimes = showTimes;
        this.movie = movie;
        this.dateMovie = dateMovie;
        this.seatDetails = seatDetails;
        this.productDetails = productDetails;
    }

    public ScreeningsDto(int id, boolean flag, ShowTimes showTimes, Movie movie, String dateMovie) {
        this.id = id;
        this.flag = flag;
        this.showTimes = showTimes;
        this.movie = movie;
        this.dateMovie = dateMovie;
    }

    public ScreeningsDto(boolean flag, ShowTimes showTimes, Movie movie, String dateMovie) {
        this.flag = flag;
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

    public Set<ProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(Set<ProductDetail> productDetails) {
        this.productDetails = productDetails;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ScreeningsDto screeningsDto = (ScreeningsDto) target;
        if (screeningsDto.dateMovie.equals("")) {
            errors.rejectValue("dateMovie", null, "Date can not empty");
        } else if (Period.between(LocalDate.parse(screeningsDto.dateMovie), LocalDate.now()).getDays() < 0) {
            errors.rejectValue("dateMovie",null,"Show date must not be less than current date!");
        }
    }
}
