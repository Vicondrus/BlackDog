package com.veterinary.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "userType", discriminatorType = DiscriminatorType.INTEGER)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int idUser;

    protected String username;

    protected String password;

    protected String fullName;

    @Column(name = "userType", insertable = false, updatable = false)
    protected UserType userType;

    protected User(){
        super();
    }

}
