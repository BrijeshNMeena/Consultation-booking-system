package com.example.LPTBE1.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "slot")
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    //to be generated randomly and used to book slots
    String slotId;

    @ManyToOne
    @JoinColumn
    Consultant consultant;

    @OneToOne
    @JoinColumn
    Client client;

    LocalDate date;

    LocalTime time;

    //marked as false when created and turned to true when booked
    boolean booked;
}
