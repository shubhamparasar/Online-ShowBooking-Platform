package com.practice.BookMyShowApplication.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeatType extends BaseModel {

    @ManyToOne
    private Shows shows;

    @ManyToOne
    private SeatType seatType;

    private int price;
}
