package com.example.LPTBE1.controller;

import com.example.LPTBE1.dto.responseDto.SlotResponseDto;
import com.example.LPTBE1.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/slot")
public class SlotController {

    @Autowired
    SlotService slotService;

    @PostMapping("/add-slot/{consultantId}/{date}/{time}")
    public ResponseEntity addSlot(@PathVariable int consultantId, @PathVariable LocalDate date, @PathVariable LocalTime time) {
        try {
            String message = slotService.addSlot(consultantId, date, time);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/book-slot/{clientId}/{slotId}")
    public ResponseEntity boolSlot(@PathVariable int clientId, @PathVariable String slotId) {
        try{
            String message = slotService.bookSlot(clientId, slotId);
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-slots")
    public ResponseEntity getSlots(@RequestParam("id") int consultantId) {
        try{
            List<SlotResponseDto> slotList = slotService.getSlots(consultantId);
            return new ResponseEntity<>(slotList, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
