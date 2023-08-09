package com.example.retro_cinema.screenings.repository;

import com.example.retro_cinema.screenings.model.Screenings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IScreeningsRepository extends JpaRepository<Screenings, Integer> {
    Page<Screenings> findAllByFlag(Pageable pageable,boolean flag);
}
