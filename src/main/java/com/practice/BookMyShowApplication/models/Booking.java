package com.practice.BookMyShowApplication.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{

    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;

    @ManyToMany
    private List<ShowSeat> showSeatList;

    @ManyToOne
    private Shows shows;

    @ManyToOne
    private User user;

    private Date bookingTime;
    private int bookingAmount;
    @OneToMany
    private List<Payment> paymentList;
}
