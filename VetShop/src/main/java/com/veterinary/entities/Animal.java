package com.veterinary.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Animal {

    @Id
    private int animalId;

    private String name;

    private String owner;

    @OneToMany(mappedBy = "animal")
    private List<Consultation> consultationList;

}
