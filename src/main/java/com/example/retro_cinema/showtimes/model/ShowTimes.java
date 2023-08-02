package com.example.retro_cinema.showtimes.model;

import com.example.retro_cinema.rooms.model.Rooms;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;

@Entity
@Table(name = "showtimes")
public class ShowTimes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private int id;
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "end_time")
    private String endTime;
    @Column(columnDefinition = "boolean default true")
    private boolean flag;
    @ManyToOne
    @JoinColumn(name = "id",referencedColumnName = "id", insertable = false, updatable = false)
    private Rooms rooms;

    public ShowTimes() {
    }

    public ShowTimes(int id, String startTime, String endTime, boolean flag, Rooms rooms) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.flag = flag;
        this.rooms = rooms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Rooms getRooms() {
        return rooms;
    }

    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }
}
