package com.vetshop.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * The type User.
 */
@AllArgsConstructor
@ToString
@SuperBuilder
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.INTEGER)
public abstract class User {

    /**
     * The Id user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int idUser;

    /**
     * The Username.
     */
    protected String username;

    /**
     * The Password.
     */
    protected String password;

    /**
     * The Full name.
     */
    protected String fullName;

    /**
     * The User type.
     */
    @Column(name = "user_type", insertable = false, updatable = false)
    protected UserType userType;

    /**
     * Get user type as string string.
     *
     * @return the string
     */
    public String getUserTypeAsString(){
        return userType.toString();
    }

    /**
     * Instantiates a new User.
     */
    protected User(){
        super();
    }

}
