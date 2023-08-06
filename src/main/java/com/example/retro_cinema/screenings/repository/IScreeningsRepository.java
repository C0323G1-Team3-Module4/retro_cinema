package com.example.retro_cinema.screenings.repository;

import com.example.retro_cinema.screenings.dto.ScreeningsDto;
import com.example.retro_cinema.screenings.model.Screenings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IScreeningsRepository extends JpaRepository<Screenings, Integer> {
    @Query(value = "select s.id, s.date_movie as dateMovie, m.movie_name as movieName, m.price, st.start_time as startTime, st.end_time as endTime, r.room_name as roomName\n" +
            "from screenings s\n" +
            "join movies m on s.movie_id = m.id\n" +
            "join movie_types mt on m.movie_type_id = mt.id\n" +
            "join showtimes st on s.showtime_id = st.id\n" +
            "join rooms r on st.room_id = r.id", nativeQuery = true)
    List<ScreeningsDto> getAllScreenings();
}
