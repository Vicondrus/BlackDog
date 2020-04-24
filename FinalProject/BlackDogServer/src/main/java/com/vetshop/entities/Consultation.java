package com.vetshop.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Consultation.
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "neededGear")
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

    @OneToMany(mappedBy = "consultation", fetch = FetchType.EAGER)
    private List<Gear> neededGear = new ArrayList<>();

    /**
     * Add gear.
     *
     * @param g the g
     */
    public void addGear(Gear g) {
        neededGear.add(g);
    }

    /**
     * Get doctor name string.
     *
     * @return the string
     */
    public String getDoctorName() {
        return doctor.getFullName();
    }

    /**
     * Get animal name string.
     *
     * @return the string
     */
    public String getAnimalName() {
        return animal.getName();
    }

    /**
     * Get owner name string.
     *
     * @return the string
     */
    public String getOwnerName() {
        return animal.getOwner();
    }

}
