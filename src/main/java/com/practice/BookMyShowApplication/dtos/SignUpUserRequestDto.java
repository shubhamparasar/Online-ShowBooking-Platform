package com.practice.BookMyShowApplication.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpUserRequestDto {

    private String Name;
    private String email;
    private String password;

}
