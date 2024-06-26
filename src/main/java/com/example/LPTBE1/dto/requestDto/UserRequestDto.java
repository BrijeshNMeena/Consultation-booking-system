package com.example.LPTBE1.dto.requestDto;

import com.example.LPTBE1.Enum.Expertise;
import com.example.LPTBE1.Enum.Gender;
import com.example.LPTBE1.Enum.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDto {

    String name;

    String email;

    String phoneNo;

    int age;

    Gender gender;

    //set as enum as there can be only limited known values to select from
    Role role;

    //last three will get ignored in case of client

    int fees;

    int experience;

    Expertise areaOfExpertise;
}
