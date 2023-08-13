package com.example.retro_cinema.seat_details.service;

import com.example.retro_cinema.seat_details.model.SeatDetails;
import com.example.retro_cinema.seat_details.repository.ISeatDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeatDetailsService implements ISeatDetailsService {
    @Autowired
    private ISeatDetailsRepository seatDetailsRepository;

    @Override
    public List<SeatDetails> getAllSeatsDetails() {
        return seatDetailsRepository.findAll();
    }

    @Override
    public List<SeatDetails> getBySeatDetailsByIdScreenings(int id) {
        System.out.println("ID:" + id);
        List<SeatDetails> seatDetailsList = new ArrayList<>();
        List<SeatDetails> seatDetails = getAllSeatsDetails();
        for (SeatDetails s : seatDetails) {
            if (s.getScreenings().getId() == id) {
                seatDetailsList.add(s);
            }
        }
        System.out.println("TEST" + seatDetailsList);
        return seatDetailsList;
    }


    @Override
    public void save(SeatDetails seatDetails) {
        seatDetailsRepository.save(seatDetails);
    }

    @Override
    public List<SeatDetails> findTicketsByUser(int userId, int screeningId, boolean flag) {
        return seatDetailsRepository.findTicketsByUser(userId, screeningId, flag);
    }

    @Override
    public void setFlagToFalse(SeatDetails ticket) {
        SeatDetails oldTicket = seatDetailsRepository.findById(ticket.getId()).get();
        oldTicket.setFlag(false);
        seatDetailsRepository.save(oldTicket);
    }

    @Override
    public List<SeatDetails> getAllSeatsDetailsByUser(int userId) {
        List<SeatDetails> seatDetailsList = new ArrayList<>();
        for (SeatDetails s: getAllSeatsDetails()) {
            if(s.getAccountUser().getId()==userId && (Period.between(LocalDate.parse(s.getScreenings().getDateMovie()), LocalDate.now()).getDays() <= 0)){
                seatDetailsList.add(s);
            }
        }
        return seatDetailsList;
    }
}