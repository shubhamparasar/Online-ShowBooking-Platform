package com.practice.BookMyShowApplication.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{

    private String num;
    private int rowId;
    private int colId;

    @ManyToOne
    private SeatType seatType;

}
