package com.practice.BookMyShowApplication.controllers;

import com.practice.BookMyShowApplication.dtos.BookMovieRequestDto;
import com.practice.BookMyShowApplication.dtos.BookMovieResponseDto;
import com.practice.BookMyShowApplication.dtos.ResponseStatus;
import com.practice.BookMyShowApplication.models.Booking;
import com.practice.BookMyShowApplication.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }


    public BookMovieResponseDto bookMovie(BookMovieRequestDto bookMovieRequestDto){

        Booking booking ;
        BookMovieResponseDto bookMovieResponseDto = new BookMovieResponseDto();
        try {
            booking = bookingService.bookMovie(bookMovieRequestDto.getShowId(),
                    bookMovieRequestDto.getShowSeatIds(),
                    bookMovieRequestDto.getUserId());

            bookMovieResponseDto.setBookingId(booking.getId());
            bookMovieResponseDto.setAmount(booking.getBookingAmount());
            bookMovieResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e){
            bookMovieResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return bookMovieResponseDto;
    }
}
