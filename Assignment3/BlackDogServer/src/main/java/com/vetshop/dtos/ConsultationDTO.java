package com.vetshop.dtos;

import com.vetshop.entities.Consultation;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

import static lombok.AccessLevel.PRIVATE;

/**
 * The type Consultation dto.
 */
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder
@ToString
@Getter
@Setter
@Data
public class ConsultationDTO implements DTO {

    private int consultationId;

    private AnimalDTO animal;

    private RegularUserDTO doctor;

    private String details;

    private String diagnostic;

    private String recommendations;

    private Date date;

    private StatusDTO status;

    /**
     * Instantiates a new Consultation dto.
     *
     * @param consultation the consultation
     */
    public ConsultationDTO(Consultation consultation) {
        consultationId = consultation.getConsultationId();
        animal = new AnimalDTO(consultation.getAnimal());
        doctor = new RegularUserDTO(consultation.getDoctor());
        details = consultation.getDetails();
        diagnostic = consultation.getDiagnostic();
        recommendations = consultation.getRecommendations();
        date = consultation.getDate();
        if (consultation.getStatus() != null)
            status = StatusDTO.valueOf(consultation.getStatus().toString());
    }

    /**
     * Get doctor name string.
     *
     * @return the string
     */
    public String getDoctorName() {
        return doctor.getFullName();
    }

    /**
     * Get animal name string.
     *
     * @return the string
     */
    public String getAnimalName() {
        return animal.getName();
    }

    /**
     * Get doctor id int.
     *
     * @return the int
     */
    public int getDoctorId() {
        return doctor.getIdUser();
    }

    /**
     * Get animal id int.
     *
     * @return the int
     */
    public int getAnimalId() {
        return animal.getAnimalId();
    }

    /**
     * Get owner name string.
     *
     * @return the string
     */
    public String getOwnerName() {
        return animal.getOwner();
    }

}
