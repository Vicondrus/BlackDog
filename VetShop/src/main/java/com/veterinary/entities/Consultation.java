package com.veterinary.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Consultation {

    @Id
    private int consultationId;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private RegularUser doctor;

    private String details;

    private String diagnostic;

}
