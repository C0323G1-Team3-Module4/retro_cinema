package com.example.retro_cinema.seats.model;

import javax.persistence.*;

@Entity
@Table(name = "seats")
public class Seats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "seat_name")
    private String seatName;
    @Column(columnDefinition = "BIT default 1")
    private boolean flag;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private double fee;

    public Seats() {
    }

    public Seats(int id, String seatName, boolean flag, double fee) {
        this.id = id;
        this.seatName = seatName;
        this.flag = flag;
        this.fee = fee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}
