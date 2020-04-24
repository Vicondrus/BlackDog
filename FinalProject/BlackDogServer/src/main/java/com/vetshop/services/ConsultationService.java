package com.vetshop.services;


import com.vetshop.dtos.ConsultationDTO;
import com.vetshop.dtos.ItemDTO;
import com.vetshop.dtos.RegularUserDTO;
import com.vetshop.dtos.StatusDTO;
import com.vetshop.services.exceptions.NoSuchEntityException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * The interface Consultation service.
 */
@Service
public interface ConsultationService {

    /**
     * Find all list.
     *
     * @return the list
     */
    List<ConsultationDTO> findAll();

    /**
     * Save consultation dto.
     *
     * @param patientId       the patient id
     * @param doctorId        the doctor id
     * @param diagnostic      the diagnostic
     * @param details         the details
     * @param recommendations the recommendations
     * @param hour            the hour
     * @param minute          the minute
     * @param date            the date
     * @param status          the status
     * @return the consultation dto
     */
    ConsultationDTO save(String patientId, String doctorId, String diagnostic, String details, String recommendations, String hour, String minute, Date date, StatusDTO status);

    /**
     * Update consultation dto.
     *
     * @param consultationId  the consultation id
     * @param patientId       the patient id
     * @param doctorId        the doctor id
     * @param diagnostic      the diagnostic
     * @param details         the details
     * @param recommendations the recommendations
     * @param hour            the hour
     * @param minute          the minute
     * @param date            the date
     * @return the consultation dto
     */
    ConsultationDTO update(int consultationId, String patientId, String doctorId, String diagnostic, String details, String recommendations, String hour, String minute, Date date);

    /**
     * Delete consultation dto.
     *
     * @param id the id
     * @return the consultation dto
     * @throws NoSuchEntityException the no such entity exception
     */
    ConsultationDTO delete(int id) throws NoSuchEntityException;

    /**
     * Find all for logged user list.
     *
     * @param regularUserDTO the regular user dto
     * @return the list
     */
    List<ConsultationDTO> findAllForLoggedUser(RegularUserDTO regularUserDTO);

    /**
     * Save consultation dto.
     *
     * @param patientId       the patient id
     * @param doctorId        the doctor id
     * @param diagnostic      the diagnostic
     * @param details         the details
     * @param recommendations the recommendations
     * @param date            the date
     * @param status          the status
     * @return the consultation dto
     */
    ConsultationDTO save(String patientId, String doctorId, String diagnostic, String details, String recommendations, Date date, StatusDTO status);

    /**
     * Update consultation dto.
     *
     * @param consultationId  the consultation id
     * @param patientId       the patient id
     * @param doctorId        the doctor id
     * @param diagnostic      the diagnostic
     * @param details         the details
     * @param recommendations the recommendations
     * @param date            the date
     * @param status          the status
     * @return the consultation dto
     */
    ConsultationDTO update(int consultationId, String patientId, String doctorId, String diagnostic, String details, String recommendations, Date date, StatusDTO status);

    /**
     * Schedule consultation dto.
     *
     * @param patientId       the patient id
     * @param doctorId        the doctor id
     * @param diagnostic      the diagnostic
     * @param details         the details
     * @param recommendations the recommendations
     * @param date            the date
     * @param status          the status
     * @param neededGear      the needed gear
     * @return the consultation dto
     */
    ConsultationDTO schedule(String patientId, String doctorId, String diagnostic, String details, String recommendations, Date date, StatusDTO status, List<ItemDTO> neededGear);

    /**
     * Begin consultation dto.
     *
     * @param consultationId the consultation id
     * @return the consultation dto
     */
    ConsultationDTO begin(int consultationId);
}
