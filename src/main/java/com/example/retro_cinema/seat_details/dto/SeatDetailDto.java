package com.example.retro_cinema.seat_details.dto;

public class SeatDetailDto {
    private int idAccount;
    private String nameSeats;
    private int idScreenings;
    private boolean flag;

    public SeatDetailDto() {
    }

    public SeatDetailDto(int idAccount, String nameSeats, int idScreenings, boolean flag) {
        this.idAccount = idAccount;
        this.nameSeats = nameSeats;
        this.idScreenings = idScreenings;
        this.flag = flag;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getNameSeats() {
        return nameSeats;
    }

    public void setNameSeats(String nameSeats) {
        this.nameSeats = nameSeats;
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

    @Override
    public String toString() {
        return "SeatDetailDto{" +
                "idAccount=" + idAccount +
                ", nameSeats='" + nameSeats + '\'' +
                ", idScreenings=" + idScreenings +
                ", flag=" + flag +
                '}';
    }
}
