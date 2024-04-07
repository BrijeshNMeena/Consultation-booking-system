package com.example.LPTBE1.service;

import com.example.LPTBE1.Enum.Expertise;
import com.example.LPTBE1.dto.requestDto.UserRequestDto;
import com.example.LPTBE1.dto.responseDto.ConsultantResponseDto;
import com.example.LPTBE1.dto.responseDto.UserResponseDto;
import com.example.LPTBE1.exception.UserNotFound;
import com.example.LPTBE1.model.Consultant;
import com.example.LPTBE1.repository.ConsultantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultantService {

    @Autowired
    ConsultantRepository consultantRepository;

    public UserResponseDto registerConsultant(UserRequestDto user) {

        Consultant consultant = new Consultant();

        //converting dto into consultant
        consultant.setName(user.getName());
        consultant.setAge(user.getAge());
        consultant.setEmail(user.getEmail());
        consultant.setGender(user.getGender());
        consultant.setPhoneNo(user.getPhoneNo());
        consultant.setFees(user.getFees());
        consultant.setExperience(user.getExperience());
        consultant.setAreaOfExpertise(user.getAreaOfExpertise());

        //saving consultant into database
        Consultant savedConsultant = consultantRepository.save(consultant);

        //creating dto from saved consultant
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(savedConsultant.getId());
        userResponseDto.setName(savedConsultant.getName());
        userResponseDto.setMessage("Congratulation registration successful");

        return userResponseDto;
    }

    public List<ConsultantResponseDto> getConsultant(Expertise expertise, int experience) {

        //getting list of all consultant
        List<Consultant> listAllConsultant = consultantRepository.findAll();

        List<ConsultantResponseDto> list = new ArrayList<>();

        //running for each loop to get to every consultant
        for (Consultant consultant : listAllConsultant) {
            //extracting experience
            int curr_experience = consultant.getExperience();
            //extracting expertise
            Expertise curr_expertise = consultant.getAreaOfExpertise();

            //filtering desired expert with minimum required or more experience
            if(curr_experience >= experience && curr_expertise.equals(expertise)) {

                //creating dto of filtered consultant
                ConsultantResponseDto consultantResponseDto = new ConsultantResponseDto();

                consultantResponseDto.setName(consultant.getName());
                consultantResponseDto.setAge(consultant.getAge());
                consultantResponseDto.setGender(consultant.getGender());
                consultantResponseDto.setFees(consultant.getFees());

                //adding to list
                list.add(consultantResponseDto);
            }
        }

        return list;
    }

    public List<ConsultantResponseDto> getAllConsultant() {

        //getting list of all consultant
        List<Consultant> listAllConsultant = consultantRepository.findAll();
        List<ConsultantResponseDto> list = new ArrayList<>();

        for (Consultant consultant : listAllConsultant) {

            //creating dto from consultant
            ConsultantResponseDto consultantResponseDto = new ConsultantResponseDto();

            consultantResponseDto.setName(consultant.getName());
            consultantResponseDto.setAge(consultant.getAge());
            consultantResponseDto.setGender(consultant.getGender());
            consultantResponseDto.setFees(consultant.getFees());

            //adding to list
            list.add(consultantResponseDto);

        }

        return list;
    }

    public ConsultantResponseDto getConsultantByEmailId(String emailId) {

        //getting list of consultant from custom function
        Consultant consultant = consultantRepository.findByEmail(emailId);

        //checking if consultant exist
        if(consultant == null) {
            throw new UserNotFound("Provide valid consultant email id");
        }

        //creating dto from consultant
        ConsultantResponseDto consultantResponseDto = new ConsultantResponseDto();

        consultantResponseDto.setName(consultant.getName());
        consultantResponseDto.setAge(consultant.getAge());
        consultantResponseDto.setGender(consultant.getGender());
        consultantResponseDto.setFees(consultant.getFees());

        return consultantResponseDto;
    }
}
