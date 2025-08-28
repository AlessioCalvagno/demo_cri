package it.cri.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private LocalDate birthDate;
    private String birthPlace;
    private String residenceAddress;
    //matricola
    private String registrationNumber;
    //grado
    private String rankValue;
    private String phoneNumber;
    private String  emailAddress;
    private String pecAddress;
    private LocalDate lastRecall;
    private String lastActivity;
    private LocalDate lastMedicalVisit;
    private LocalDate nextMedicalVisit;
    //Quota associativa
    private Double fee;

}
