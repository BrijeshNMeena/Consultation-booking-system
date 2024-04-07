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

    public ClientResponseDto getClient(int clientId) {
        Optional<Client> optionalClient = clientRepository.findById(clientId);

        if (optionalClient.isEmpty())
            throw new UserNotFound("Invalid Client ID!");

        Client client = optionalClient.get();

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setId(client.getId());
        clientResponseDto.setName(client.getName());
        clientResponseDto.setEmail(client.getEmail());
        clientResponseDto.setMessage("Details fetched successfully.");

        return clientResponseDto;
    }


    public UserResponseDto registerClient(UserRequestDto user) {
        Client client = new Client();

        client.setName(user.getName());
        client.setAge(user.getAge());
        client.setEmail(user.getEmail());
        client.setGender(user.getGender());
        client.setPhoneNo(user.getPhoneNo());

        Client savedClient = clientRepository.save(client);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(savedClient.getId());
        userResponseDto.setName(savedClient.getName());
        userResponseDto.setMessage("Congratulation registration successful");

        return userResponseDto;
    }
}
