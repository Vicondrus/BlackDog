package com.veterinary.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
@Getter
@Setter
@Entity
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int consultationId;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private RegularUser doctor;

    private String details;

    private String diagnostic;

    private String recommendations;

    private Date date;

    public String getDoctorName(){
        return doctor.getFullName();
    }

    public String getAnimalName(){
        return animal.getName();
    }

    public String getOwnerName(){
        return animal.getOwner();
    }

}
