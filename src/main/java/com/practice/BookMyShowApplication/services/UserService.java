package com.practice.BookMyShowApplication.services;

import com.practice.BookMyShowApplication.exceptions.PasswordMismatchException;
import com.practice.BookMyShowApplication.exceptions.UserAlreadyExistException;
import com.practice.BookMyShowApplication.exceptions.UserNotFoundException;
import com.practice.BookMyShowApplication.models.User;
import com.practice.BookMyShowApplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UserService {

    private UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User login(String email, String password){

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException();
        }
        User user = optionalUser.get();
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
            throw new PasswordMismatchException();
        }
        return user;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public User signUp(String userName, String password, String emailId){

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Optional<User> optionalUser = userRepository.findByEmail(emailId);

        if(!optionalUser.isEmpty()){
            throw new UserAlreadyExistException();
        }

        User user = new User();
        user.setName(userName);
        user.setEmail(emailId);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        User savedUser = userRepository.save(user);

        return savedUser;
    }
}
