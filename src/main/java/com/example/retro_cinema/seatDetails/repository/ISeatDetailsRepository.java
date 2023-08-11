package com.example.retro_cinema.seatDetails.repository;

import com.example.retro_cinema.seatDetails.dto.SeatDetailInformation;
import com.example.retro_cinema.seatDetails.model.SeatDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISeatDetailsRepository extends JpaRepository<SeatDetails,Integer> {
    @Query(value = "select a.id as idAccount,s.id as idSeats, sc.id as idScreenings, sd.flag\n" +
            "    from seat_details sd\n" +
            "    join seats s on sd.seat_id = s.id\n" +
            "    join screenings sc on sd.screening_id = sc.id\n" +
            "    join accounts a on sd.account_id = a.id",nativeQuery = true)
    List<SeatDetailInformation> getListInformation();
}
