package com.practice.BookMyShowApplication.controllers;


import com.practice.BookMyShowApplication.dtos.ResponseStatus;
import com.practice.BookMyShowApplication.dtos.SignUpUserRequestDto;
import com.practice.BookMyShowApplication.dtos.SignUpUserResponseDto;
import com.practice.BookMyShowApplication.models.User;
import com.practice.BookMyShowApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }
    public SignUpUserResponseDto signUp(SignUpUserRequestDto requestDto){

       SignUpUserResponseDto responseDto = new SignUpUserResponseDto();
       try{
           User user = userService.signUp(requestDto.getName(),
                   requestDto.getPassword(),
                   requestDto.getEmail());
           responseDto.setUserId(user.getId());
           responseDto.setResponseStatus(ResponseStatus.SUCCESS);
       }
       catch (Exception e){
           responseDto.setResponseStatus(ResponseStatus.FAILURE);
       }
       return responseDto;
    }
}
