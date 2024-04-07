package com.example.LPTBE1.controller;

import com.example.LPTBE1.dto.responseDto.ClientResponseDto;
import com.example.LPTBE1.exception.UserNotFound;
import com.example.LPTBE1.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/get-client")
    public ResponseEntity getClient(@RequestParam("id") int clientId) {
        try{
            ClientResponseDto clientResponseDto = clientService.getClient(clientId);
            return new ResponseEntity(clientResponseDto, HttpStatus.FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
