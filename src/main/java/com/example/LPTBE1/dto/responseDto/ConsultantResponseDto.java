package com.example.LPTBE1.dto.responseDto;

import com.example.LPTBE1.Enum.Expertise;
import com.example.LPTBE1.Enum.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConsultantResponseDto {

    String name;

    int age;

    Gender gender;

    int fees;

}
