package com.example.retro_cinema.seatDetails.dto;

public class SeatDetailDto {
    private int idAccount;
    private int id                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        Seats;
    private int idScreenings;
    private boolean flag;

    public SeatDetailDto() {
    }

    public SeatDetailDto(int idAccount, int idSeats, int idScreenings, boolean flag) {
        this.idAccount = idAccount;
        this.idSeats = idSeats;
        this.idScreenings = idScreenings;
        this.flag = flag;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public int getIdSeats() {
        return idSeats;
    }

    public void setIdSeats(int idSeats) {
        this.idSeats = idSeats;
    }

    public int getIdScreenings() {
        return idScreenings;
    }

    public void setIdScreenings(int idScreenings) {
        this.idScreenings = idScreenings;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
