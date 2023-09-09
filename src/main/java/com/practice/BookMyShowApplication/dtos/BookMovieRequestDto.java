package com.practice.BookMyShowApplication.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class BookMovieRequestDto {

    private List<Long> showSeatIds;
    private Long showId;

    private Long userId;
}
