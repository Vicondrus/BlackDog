package com.veterinary.dtos;

import com.veterinary.entities.Consultation;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

    public String getDoctorName(){
        return doctor.getFullName();
    }

    public String getAnimalName(){
        return animal.getName();
    }

    public String getOwnerName(){
        return animal.getOwner();
    }

    public StringProperty animalProperty(){
        StringProperty sp = new SimpleStringProperty();
        sp.setValue(getAnimalName());
        return sp;
    }

    public ConsultationDTO(Consultation consultation){
        consultationId = consultation.getConsultationId();
        animal = new AnimalDTO(consultation.getAnimal());
        doctor = new RegularUserDTO(consultation.getDoctor());
        details = consultation.getDetails();
        diagnostic = consultation.getDiagnostic();
        recommendations = consultation.getRecommendations();
        date = consultation.getDate();
    }

}
