package com.veterinary.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "userType")
public abstract class User {

    @Id
    private int idUser;

    private String username;

    private String password;

    private String fullName;

    @Column(name = "userType", insertable = false, updatable = false)
    private UserType userType;

}
