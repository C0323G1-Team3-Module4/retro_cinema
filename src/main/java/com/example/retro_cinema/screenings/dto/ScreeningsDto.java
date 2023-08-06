package com.example.retro_cinema.screenings.dto;

public class ScreeningsDto implements IScreeningsDto{
    private String id;
    private String dateMovie;
    private String movieName;
    private String price;
    private String startTime;
    private String endTime;
    private String roomName;
    private String showtimeId;

    public ScreeningsDto() {
    }

    public ScreeningsDto(String id, String dateMovie, String movieName, String price, String startTime, String endTime, String roomName, String showtimeId) {
        this.id = id;
        this.dateMovie = dateMovie;
        this.movieName = movieName;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomName = roomName;
        this.showtimeId = showtimeId;
    }
    public ScreeningsDto(String showtimeId,String movieName,String dateMovie){
        this.showtimeId = showtimeId;
        this.movieName = movieName;
        this.dateMovie = dateMovie;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getDateMovie() {
        return null;
    }

    @Override
    public String getMovieName() {
        return null;
    }

    @Override
    public String getPrice() {
        return null;
    }

    @Override
    public String getStartTime() {
        return null;
    }

    @Override
    public String getEndTime() {
        return null;
    }

    @Override
    public String getRoomName() {
        return null;
    }

    @Override
    public String getShowtimeId() {
        return null;
    }
}
