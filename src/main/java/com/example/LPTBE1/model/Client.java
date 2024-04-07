package com.example.LPTBE1.model;

import com.example.LPTBE1.Enum.Gender;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "client")
public class Client {

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

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    Slot slot;

}
