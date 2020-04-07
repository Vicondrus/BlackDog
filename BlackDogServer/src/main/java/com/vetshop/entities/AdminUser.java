package com.vetshop.entities;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@ToString(callSuper = true)
@Builder
@Getter
@Setter
@Entity
@DiscriminatorValue("1")
public class AdminUser extends User{

}
