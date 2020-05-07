package com.vetshop.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Regular user.
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "consultations", callSuper = true)
@SuperBuilder
@Getter
@Setter
@Entity
@DiscriminatorValue("0")
public class RegularUser extends User {

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    private List<Consultation> consultations = new ArrayList<Consultation>();

    /**
     * Get consulted animals list.
     *
     * @return the list
     */
    public List<Animal> getConsultedAnimals() {
        return consultations.stream().map(Consultation::getAnimal).collect(Collectors.toList());
    }

}
