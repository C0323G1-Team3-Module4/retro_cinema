package com.example.retro_cinema.rooms.model;

import com.example.retro_cinema.showtimes.model.ShowTimes;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "room_name")
    private String roomName;
    private int capacity;
  
    @Column(columnDefinition = "boolean default true")
    private boolean flag;
    @OneToMany(mappedBy = "rooms")
    private Set<ShowTimes> showTimes;
    public Rooms() {
    }

    public Rooms(int id, String roomName, int capacity, boolean flag) {
        this.id = id;
        this.roomName = roomName;
        this.capacity = capacity;
        this.flag = flag;
    }

    public Set<ShowTimes> getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(Set<ShowTimes> showTimes) {
        this.showTimes = showTimes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
