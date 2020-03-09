package com.veterinary.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int animalId;

    private String name;

    private String owner;

    private String species;

    @OneToMany(mappedBy = "animal", fetch = FetchType.EAGER)
    private List<Consultation> consultationList = new ArrayList<Consultation>();

}
