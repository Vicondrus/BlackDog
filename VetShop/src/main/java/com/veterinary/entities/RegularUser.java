package com.veterinary.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@DiscriminatorValue("1")
public class RegularUser extends User{

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    private List<Consultation> consultations = new ArrayList<Consultation>();

    public List<Animal> getConsultedAnimals(){
        return consultations.stream().map(x -> x.getAnimal()).collect(Collectors.toList());
    }

}
