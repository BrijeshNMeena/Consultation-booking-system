package com.example.LPTBE1.controller;

import com.example.LPTBE1.Enum.Expertise;
import com.example.LPTBE1.dto.responseDto.ClientResponseDto;
import com.example.LPTBE1.dto.responseDto.ConsultantResponseDto;
import com.example.LPTBE1.dto.responseDto.SlotResponseDto;
import com.example.LPTBE1.service.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/consultant")
public class ConsultantController {

    @Autowired
    ConsultantService consultantService;

    @GetMapping("/get-consultant")
    public ResponseEntity getConsultant(@RequestParam("area_of_expertise") Expertise expertise, @RequestParam("Min_experience") int experience) {
        try {
            List<ConsultantResponseDto> list = consultantService.getConsultant(expertise, experience);
            return new ResponseEntity(list, HttpStatus.FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all-consultant")
    public ResponseEntity getConsultant() {
        try {
            List<ConsultantResponseDto> list = consultantService.getAllConsultant();
            return new ResponseEntity(list, HttpStatus.FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
