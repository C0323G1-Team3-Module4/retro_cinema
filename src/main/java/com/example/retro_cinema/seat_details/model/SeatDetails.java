package com.example.retro_cinema.seat_details.model;

import com.example.retro_cinema.screenings.model.Screenings;
import com.example.retro_cinema.seats.model.Seats;
import com.example.retro_cinema.user.model.AccountUser;

import javax.persistence.*;

@Entity
@Table(name = "seat_details")
public class SeatDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "BIT default 1")
    private boolean flag;
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountUser accountUser;
    @ManyToOne
    @JoinColumn(name = "screening_id", referencedColumnName = "id")
    private Screenings screenings;
    @ManyToOne
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private Seats seats;

    public SeatDetails() {
    }

    public SeatDetails(boolean flag, AccountUser accountUser, Screenings screenings, Seats seats) {
        this.flag = flag;
        this.accountUser = accountUser;
        this.screenings = screenings;
        this.seats = seats;
    }

    public SeatDetails(int id, boolean flag) {
        this.id = id;
        this.flag = flag;
    }

    public SeatDetails(int id, boolean flag, AccountUser accountUser, Screenings screenings, Seats seats) {
        this.id = id;
        this.flag = flag;
        this.accountUser = accountUser;
        this.screenings = screenings;
        this.seats = seats;
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

    public AccountUser getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(AccountUser accountUser) {
        this.accountUser = accountUser;
    }

    public Screenings getScreenings() {
        return screenings;
    }

    public void setScreenings(Screenings screenings) {
        this.screenings = screenings;
    }

    public Seats getSeats() {
        return seats;
    }

    public void setSeats(Seats seats) {
        this.seats = seats;
    }
}
