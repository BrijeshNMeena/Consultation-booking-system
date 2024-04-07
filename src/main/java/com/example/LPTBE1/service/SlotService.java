package com.example.LPTBE1.service;

import com.example.LPTBE1.dto.responseDto.SlotResponseDto;
import com.example.LPTBE1.exception.SlotNotFound;
import com.example.LPTBE1.exception.UserNotFound;
import com.example.LPTBE1.model.Client;
import com.example.LPTBE1.model.Consultant;
import com.example.LPTBE1.model.Slot;
import com.example.LPTBE1.repository.ClientRepository;
import com.example.LPTBE1.repository.ConsultantRepository;
import com.example.LPTBE1.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SlotService {

    @Autowired
    ConsultantRepository consultantRepository;

    @Autowired
    SlotRepository slotRepository;

    @Autowired
    ClientRepository clientRepository;

    public String addSlot(int consultantId, LocalDate date, LocalTime time) {
        Optional<Consultant> optionalConsultant = consultantRepository.findById(consultantId);
        if(optionalConsultant.isEmpty()) {
            throw new UserNotFound("Invalid consultant Id");
        }

        Consultant consultant = optionalConsultant.get();

        Slot slot = new Slot();
        slot.setSlotId(String.valueOf(UUID.randomUUID()));
        slot.setConsultant(consultant);
        slot.setDate(date);
        slot.setTime(time);
        slot.setBooked(false);

        slotRepository.save(slot);

        return "Slot added successfully";
    }

    public String bookSlot(int clientId, String slotId) {

        Optional<Slot> optionalSlot = Optional.ofNullable(slotRepository.findBySlotId(slotId));

        if(optionalSlot.isEmpty()) {
            throw new SlotNotFound("Provide a valid session Id");
        }

        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if(optionalClient.isEmpty()) {
            throw new UserNotFound("Provide a valid client Id");
        }

        Client client = optionalClient.get();

        Slot slot = optionalSlot.get();
        slot.setClient(client);
        slot.setBooked(true);

        client.setSlot(slot);
        clientRepository.save(client);

        return "Congratulation! Session booked successfully";
    }

    public List<SlotResponseDto> getSlots(int consultantId) {

        Optional<Consultant> optionalConsultant = consultantRepository.findById(consultantId);

        if (optionalConsultant.isEmpty()){
            throw new UserNotFound("Provide valid consultant Id");
        }

        Consultant consultant = optionalConsultant.get();
        List<Slot> slotList = consultant.getSlotList();

        List<SlotResponseDto> slotResponseDtoList = new ArrayList<>();

        for(Slot slot : slotList) {
            if(!slot.isBooked()) {
                SlotResponseDto slotResponseDto = new SlotResponseDto();
                slotResponseDto.setSlotId(slot.getSlotId());
                slotResponseDto.setDate(slot.getDate());
                slotResponseDto.setTime(slot.getTime());

                slotResponseDtoList.add(slotResponseDto);
            }
        }

        return slotResponseDtoList;
    }

}
