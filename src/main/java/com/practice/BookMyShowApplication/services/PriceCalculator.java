package com.practice.BookMyShowApplication.services;

import com.practice.BookMyShowApplication.models.ShowSeat;
import com.practice.BookMyShowApplication.models.ShowSeatType;
import com.practice.BookMyShowApplication.models.Shows;
import com.practice.BookMyShowApplication.repositories.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculator {

    private ShowSeatTypeRepository showSeatTypeRepository;

    @Autowired
    public PriceCalculator(ShowSeatTypeRepository showSeatTypeRepository){
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public int calculatePrice(List<ShowSeat> showSeats, Shows shows){

        int amount = 0;
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShows(shows);
        for(ShowSeat showSeat : showSeats){
            for(ShowSeatType showSeatType : showSeatTypes){
                if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())){
                    amount += showSeatType.getPrice();
                    break;
                }
            }
        }
        return amount;
    }

}
