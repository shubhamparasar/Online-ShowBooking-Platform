package com.practice.BookMyShowApplication.services;

import com.practice.BookMyShowApplication.exceptions.SeatNotAvialableException;
import com.practice.BookMyShowApplication.exceptions.ShowNotFoundException;
import com.practice.BookMyShowApplication.exceptions.UserNotFoundException;
import com.practice.BookMyShowApplication.models.*;
import com.practice.BookMyShowApplication.repositories.BookingRepository;
import com.practice.BookMyShowApplication.repositories.ShowRepository;
import com.practice.BookMyShowApplication.repositories.ShowSeatRepository;
import com.practice.BookMyShowApplication.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private PriceCalculator priceCalculator;
    private final BookingRepository bookingRepository;

    public BookingService(UserRepository userRepository,
                          ShowSeatRepository showSeatRepository,
                          ShowRepository showRepository,
                          PriceCalculator priceCalculator,
                          BookingRepository bookingRepository){
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.userRepository = userRepository;
        this.priceCalculator = priceCalculator;
        this.bookingRepository = bookingRepository;
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long showId, List<Long> showSeatIds, Long userId){

         // GET USER
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException();
        }
        User bookedBy = userOptional.get();

        // GET SHOW
        Optional<Shows> showsOptional = showRepository.findById(showId);
        if(showsOptional.isEmpty()){
            throw new ShowNotFoundException();
        }
        Shows bookedShow = showsOptional.get();

        // GET SHOWSEAT LIST
        List<ShowSeat> showSeatList = showSeatRepository.findAllById(showSeatIds);
        // if seat seat status is not available, or if the status is booked and blocked for less then 15 mins, then t
        // throw exception as those particular seats are not available
        for(ShowSeat showSeat : showSeatList){
            if(!(showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE) ||
                    (showSeat.getShowSeatStatus().equals(ShowSeatStatus.BOOKED) &&
                            Duration.between(showSeat.getBlockedAt().toInstant(), new Date().toInstant()).toMinutes() > 15))){

                throw new SeatNotAvialableException();
            }
        }

        // else block those seat as it is available
        List<ShowSeat> bookedShowSeat = new ArrayList<>();
        for(ShowSeat showSeat : showSeatList){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            bookedShowSeat.add(showSeat);
        }

        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setUser(bookedBy);
        booking.setShows(bookedShow);
        booking.setShowSeatList(bookedShowSeat);
        booking.setBookingTime(new Date());
        booking.setPaymentList(new ArrayList<>());
        booking.setBookingAmount(priceCalculator.calculatePrice(bookedShowSeat, bookedShow));

        Booking savedBooking = bookingRepository.save(booking);
        return savedBooking;



    }
}
