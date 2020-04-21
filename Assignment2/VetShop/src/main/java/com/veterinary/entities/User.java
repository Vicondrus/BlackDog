package com.veterinary.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@AllArgsConstructor
@ToString
@SuperBuilder
@Getter
@Setter
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

    public String getUserTypeAsString(){
        return userType.toString();
    }

    protected User(){
        super();
    }

}
