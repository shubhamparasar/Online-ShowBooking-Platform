package com.practice.BookMyShowApplication.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Screen extends BaseModel{

    private String Name;
    @OneToMany
    private List<Seat> seatList;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> featureList;
}
