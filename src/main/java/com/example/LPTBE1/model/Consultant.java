package com.example.LPTBE1.model;

import com.example.LPTBE1.Enum.Expertise;
import com.example.LPTBE1.Enum.Gender;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "consultant")
public class Consultant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    @Column(unique = true)
    String email;

    String phoneNo;

    int age;

    @Enumerated(EnumType.STRING)
    Gender gender;

    int experience;

    int fees;

    @Enumerated(EnumType.STRING)
    Expertise areaOfExpertise;

    @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL)
    List<Slot> slotList;
}
