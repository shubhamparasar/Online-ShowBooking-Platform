package com.practice.BookMyShowApplication.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpUserResponseDto {
    private ResponseStatus responseStatus;
    private Long userId;
}
