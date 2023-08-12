package com.example.retro_cinema.seatDetails.service;

import com.example.retro_cinema.seatDetails.model.SeatDetails;
import com.example.retro_cinema.seatDetails.repository.ISeatDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<SeatDetails> seatDetailsList = new ArrayList<>();
        List<SeatDetails> seatDetails = getAllSeatsDetails();
        for (SeatDetails s : seatDetails) {
            if (s.getScreenings().getId() == id) {
                seatDetailsList.add(s);
            }
        }
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
}