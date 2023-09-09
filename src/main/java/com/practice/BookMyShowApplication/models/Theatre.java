package com.practice.BookMyShowApplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel{

    private String Name;

    @ManyToOne
    private Region region;

    @OneToMany
    private List<Screen> screenList;
}
