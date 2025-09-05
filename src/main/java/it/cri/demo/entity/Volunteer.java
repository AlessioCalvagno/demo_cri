package it.cri.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
//@Data
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "volunteer")
    private List<AssociativeFee> associativeFees;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "volunteer")
    private List<Brevet> brevets;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "volunteer")
    private List<MedicalVisit> medicalVisits;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "volunteer")
    private List<Promotion> promotions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "volunteer")
    private List<Qualification> qualifications;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "volunteer")
    private List<Recall> recalls;


    @Override
    public String toString() {
        return rankValue + " " + name + " " + surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getRankValue() {
        return rankValue;
    }

    public void setRankValue(String rankValue) {
        this.rankValue = rankValue;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPecAddress() {
        return pecAddress;
    }

    public void setPecAddress(String pecAddress) {
        this.pecAddress = pecAddress;
    }

    public LocalDate getLastRecall() {
        return lastRecall;
    }

    public void setLastRecall(LocalDate lastRecall) {
        this.lastRecall = lastRecall;
    }

    public String getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(String lastActivity) {
        this.lastActivity = lastActivity;
    }

    public LocalDate getLastMedicalVisit() {
        return lastMedicalVisit;
    }

    public void setLastMedicalVisit(LocalDate lastMedicalVisit) {
        this.lastMedicalVisit = lastMedicalVisit;
    }

    public LocalDate getNextMedicalVisit() {
        return nextMedicalVisit;
    }

    public void setNextMedicalVisit(LocalDate nextMedicalVisit) {
        this.nextMedicalVisit = nextMedicalVisit;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }
}
