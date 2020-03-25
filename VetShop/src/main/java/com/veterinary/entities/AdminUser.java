package com.veterinary.entities;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@ToString(callSuper = true)
@Builder
@Getter
@Setter
@Entity
@DiscriminatorValue("1")
public class AdminUser extends User{

}
