package com.veterinary.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@DiscriminatorValue("REGULAR")
@ToString(of = {"id","username","password","fullName"})
public class RegularUser extends User{

    @OneToMany(mappedBy = "doctor")
    private List<Consultation> consultations = new ArrayList<>();

}
