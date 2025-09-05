package it.cri.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table
@Data
public class MedicalVisit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    //next date = date + 3 months

    //Evaluate a dedicated Entity for doctor, and a 1:N relationship
    private String doctor;

    @Lob @Basic(fetch = FetchType.LAZY)
    private byte[] file;

    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;
}
