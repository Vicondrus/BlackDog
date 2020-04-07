package com.vetshop.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder
@ToString
@Getter
@Setter
@Data
public class ConsultationDTO implements DTO{

    private int consultationId;

    private AnimalDTO animal;

    private UserDTO doctor;

    private String details;

    private String diagnostic;

    private String recommendations;

    private Date date;

    public String getDoctorName(){
        return doctor.getFullName();
    }

    public String getAnimalName(){
        return animal.getName();
    }

    public String getOwnerName(){
        return animal.getOwner();
    }

}
