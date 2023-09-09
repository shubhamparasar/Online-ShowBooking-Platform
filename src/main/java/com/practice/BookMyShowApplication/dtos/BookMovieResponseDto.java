package com.practice.BookMyShowApplication.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMovieResponseDto {
    private ResponseStatus responseStatus;
    private int amount;

    private int bookingId;
}
