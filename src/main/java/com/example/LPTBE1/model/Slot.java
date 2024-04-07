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

    String slotId;

    @ManyToOne
    @JoinColumn
    Consultant consultant;

    @OneToOne
    @JoinColumn
    Client client;

    LocalDate date;

    LocalTime time;

    boolean booked;
}
