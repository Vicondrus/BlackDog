package com.vetshop.dtos;

import com.vetshop.entities.Consultation;
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

    private RegularUserDTO doctor;

    private String details;

    private String diagnostic;

    private String recommendations;

    private Date date;

    private StatusDTO status;

    public String getDoctorName(){
        return doctor.getFullName();
    }

    public String getAnimalName(){
        return animal.getName();
    }

    public int getDoctorId(){
        return doctor.getIdUser();
    }

    public int getAnimalId(){
        return animal.getAnimalId();
    }

    public String getOwnerName(){
        return animal.getOwner();
    }

    public ConsultationDTO(Consultation consultation){
        consultationId = consultation.getConsultationId();
        animal = new AnimalDTO(consultation.getAnimal());
        doctor = new RegularUserDTO(consultation.getDoctor());
        details = consultation.getDetails();
        diagnostic = consultation.getDiagnostic();
        recommendations = consultation.getRecommendations();
        date = consultation.getDate();
        status = StatusDTO.valueOf(consultation.getStatus().toString());
    }

}
