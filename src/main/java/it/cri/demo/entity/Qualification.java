package it.cri.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Qualification implements RelatedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String committee;

    @Lob @Basic(fetch = FetchType.LAZY)
    private byte[] file;

    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;
}
