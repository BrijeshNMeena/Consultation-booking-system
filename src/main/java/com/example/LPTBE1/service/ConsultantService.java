package com.example.LPTBE1.service;

import com.example.LPTBE1.Enum.Expertise;
import com.example.LPTBE1.dto.requestDto.UserRequestDto;
import com.example.LPTBE1.dto.responseDto.ConsultantResponseDto;
import com.example.LPTBE1.dto.responseDto.UserResponseDto;
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

        consultant.setName(user.getName());
        consultant.setAge(user.getAge());
        consultant.setEmail(user.getEmail());
        consultant.setGender(user.getGender());
        consultant.setPhoneNo(user.getPhoneNo());
        consultant.setFees(user.getFees());
        consultant.setExperience(user.getExperience());
        consultant.setAreaOfExpertise(user.getAreaOfExpertise());

        Consultant savedConsultant = consultantRepository.save(consultant);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(savedConsultant.getId());
        userResponseDto.setName(savedConsultant.getName());
        userResponseDto.setMessage("Congratulation registration successful");

        return userResponseDto;
    }

    public List<ConsultantResponseDto> getConsultant(Expertise expertise, int experience) {

        List<Consultant> listAllConsultant = consultantRepository.findAll();
        List<ConsultantResponseDto> list = new ArrayList<>();

        for (Consultant consultant : listAllConsultant) {
            int curr_experience = consultant.getExperience();
            Expertise curr_expertise = consultant.getAreaOfExpertise();
            if(curr_experience >= experience && curr_expertise.equals(expertise)) {
                ConsultantResponseDto consultantResponseDto = new ConsultantResponseDto();

                consultantResponseDto.setName(consultant.getName());
                consultantResponseDto.setAge(consultant.getAge());
                consultantResponseDto.setGender(consultant.getGender());
                consultantResponseDto.setFees(consultant.getFees());

                list.add(consultantResponseDto);
            }
        }

        return list;
    }

    public List<ConsultantResponseDto> getAllConsultant() {
        List<Consultant> listAllConsultant = consultantRepository.findAll();
        List<ConsultantResponseDto> list = new ArrayList<>();

        for (Consultant consultant : listAllConsultant) {

            ConsultantResponseDto consultantResponseDto = new ConsultantResponseDto();

            consultantResponseDto.setName(consultant.getName());
            consultantResponseDto.setAge(consultant.getAge());
            consultantResponseDto.setGender(consultant.getGender());

            list.add(consultantResponseDto);

        }

        return list;
    }

}
