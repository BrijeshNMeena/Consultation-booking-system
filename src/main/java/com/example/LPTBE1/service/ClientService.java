package com.example.LPTBE1.service;

import com.example.LPTBE1.dto.requestDto.UserRequestDto;
import com.example.LPTBE1.dto.responseDto.ClientResponseDto;
import com.example.LPTBE1.dto.responseDto.UserResponseDto;
import com.example.LPTBE1.exception.UserNotFound;
import com.example.LPTBE1.model.Client;
import com.example.LPTBE1.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public ClientResponseDto getClient(String emailId) {
        Client client = clientRepository.findByEmail(emailId);

        //checking if client exist
        if (client == null)
            throw new UserNotFound("Invalid Client Email Id!");

        //creating object of client response dto
        //small dto hence no need for transformer class
        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setId(client.getId());
        clientResponseDto.setName(client.getName());
        clientResponseDto.setEmail(client.getEmail());
        clientResponseDto.setMessage("Details fetched successfully.");

        return clientResponseDto;
    }


    public UserResponseDto registerClient(UserRequestDto user) {

        //creating object of client class
        Client client = new Client();

        //converting dto to client
        client.setName(user.getName());
        client.setAge(user.getAge());
        client.setEmail(user.getEmail());
        client.setGender(user.getGender());
        client.setPhoneNo(user.getPhoneNo());

        //saving client into database
        Client savedClient = clientRepository.save(client);

        //creating dto from saved client
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(savedClient.getId());
        userResponseDto.setName(savedClient.getName());
        userResponseDto.setMessage("Congratulation registration successful");

        return userResponseDto;
    }
}
