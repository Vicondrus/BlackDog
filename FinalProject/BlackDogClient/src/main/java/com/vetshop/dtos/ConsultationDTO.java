package com.vetshop.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

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

    private UserDTO doctor;

    private String details;

    private String diagnostic;

    private String recommendations;

    private Date date;

    private StatusDTO status;

    private List<ItemDTO> items;

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
     * Get owner name string.
     *
     * @return the string
     */
    public String getOwnerName() {
        return animal.getOwner();
    }

    /**
     * Get animal species string.
     *
     * @return the string
     */
    public String getAnimalSpecies() {
        return animal.getSpecies();
    }

    /**
     * Get status string string.
     *
     * @return the string
     */
    public String getStatusString() {
        return status.toString();
    }
}
