package com.example.LPTBE1.controller;

import com.example.LPTBE1.dto.requestDto.UserRequestDto;
import com.example.LPTBE1.dto.responseDto.UserResponseDto;
import com.example.LPTBE1.service.ClientService;
import com.example.LPTBE1.service.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    ClientService clientService;

    @Autowired
    ConsultantService consultantService;


    //registering users
    @PostMapping("/register-user")
    public ResponseEntity registerUser(@RequestBody UserRequestDto user) {

        //filtering if the user is a client or a consultant
        if(user.getRole().toString().equals("CLIENT")) {
            try{
                UserResponseDto userResponseDto = clientService.registerClient(user);
                return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
            }
            catch (Exception e) {
                return new ResponseEntity("Email already exist!", HttpStatus.BAD_REQUEST);
            }
        } else if (user.getRole().toString().equals("CONSULTANT")) {
            try{
                UserResponseDto userResponseDto = consultantService.registerConsultant(user);
                return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
            }
            catch (Exception e) {
                return new ResponseEntity("Email already exist!", HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>("Enter valid Role", HttpStatus.FORBIDDEN);
    }
}
