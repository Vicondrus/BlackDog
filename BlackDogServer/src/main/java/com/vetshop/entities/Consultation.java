package com.vetshop.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

/**
 * The type Consultation.
 */
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

    private Status status;

    /**
     * Get doctor name string.
     *
     * @return the string
     */
    public String getDoctorName(){
        return doctor.getFullName();
    }

    /**
     * Get animal name string.
     *
     * @return the string
     */
    public String getAnimalName(){
        return animal.getName();
    }

    /**
     * Get owner name string.
     *
     * @return the string
     */
    public String getOwnerName(){
        return animal.getOwner();
    }

}
