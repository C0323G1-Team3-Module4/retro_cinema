package com.example.retro_cinema.seatDetails.repository;

import com.example.retro_cinema.seatDetails.model.SeatDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISeatDetailsRepository extends JpaRepository<SeatDetails,Integer> {
    @Query(value = " select * from retro_cinema.seat_details WHERE account_id = :userId AND screening_id = :screenigId AND flag = :flag ", nativeQuery = true)
    List<SeatDetails> findTicketsByUser(@Param("userId") int userId, @Param("screenigId") int screenigId,  @Param("flag") boolean flag);
}
