package com.example.retro_cinema.showtimes.model;

import com.example.retro_cinema.rooms.model.Rooms;
import com.example.retro_cinema.screenings.model.Screenings;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.Set;

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
    @JoinColumn(name = "room_id",referencedColumnName = "id", insertable = false, updatable = false)
    private Rooms rooms;
    @OneToMany(mappedBy = "showTimes")
    private Set<Screenings> screeningsSet;

    public ShowTimes() {
    }

    public ShowTimes(int id, String startTime, String endTime, boolean flag, Rooms rooms) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.flag = flag;
        this.rooms = rooms;
    }

    public ShowTimes(int id, String startTime, String endTime, boolean flag, Rooms rooms, Set<Screenings> screeningsSet) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.flag = flag;
        this.rooms = rooms;
        this.screeningsSet = screeningsSet;
    }

    public Set<Screenings> getScreeningsSet() {
        return screeningsSet;
    }

    public void setScreeningsSet(Set<Screenings> screeningsSet) {
        this.screeningsSet = screeningsSet;
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
