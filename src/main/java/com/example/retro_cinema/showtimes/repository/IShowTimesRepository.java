package com.example.retro_cinema.showtimes.repository;

import com.example.retro_cinema.showtimes.dto.ShowTimesDto;
import com.example.retro_cinema.showtimes.model.ShowTimes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IShowTimesRepository extends JpaRepository<ShowTimes, Integer> {
    @Query(value = "select s.id, s.start_time as startTime, s.end_time as endTime, s.flag, r.room_name as roomName, r.capacity \n" +
            "from showtimes s\n" +
            "join rooms r on s.room_id = r.id", nativeQuery = true)
    List<ShowTimesDto> getShowTimeDto();
}
