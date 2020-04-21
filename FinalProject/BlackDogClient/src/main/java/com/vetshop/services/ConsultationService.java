package com.vetshop.services;

import com.itextpdf.text.DocumentException;
import com.vetshop.dtos.AnimalDTO;
import com.vetshop.dtos.ConsultationDTO;
import com.vetshop.dtos.StatusDTO;
import com.vetshop.dtos.UserDTO;
import com.vetshop.exceptions.FieldException;
import com.vetshop.report.Report;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * The interface Consultation service.
 */
public interface ConsultationService {
    /**
     * Post find all for logged user list.
     *
     * @return the list
     */
    List<ConsultationDTO> postFindAllForLoggedUser();

    /**
     * Report consultation report.
     *
     * @param cons the cons
     * @param path the path
     * @param type the type
     * @return the report
     * @throws IOException       the io exception
     * @throws DocumentException the document exception
     */
    Report reportConsultation(ConsultationDTO cons, String path, String type) throws IOException, DocumentException;

    /**
     * Post create consultation consultation dto.
     *
     * @param patinet         the patinet
     * @param doctor          the doctor
     * @param diagnostic      the diagnostic
     * @param details         the details
     * @param recommendations the recommendations
     * @param hour            the hour
     * @param minute          the minute
     * @param date            the date
     * @param status          the status
     * @return the consultation dto
     * @throws FieldException the field exception
     */
    ConsultationDTO postCreateConsultation(AnimalDTO patinet, UserDTO doctor, String diagnostic, String details, String recommendations, String hour, String minute, Date date, StatusDTO status) throws FieldException;

    /**
     * Post schedule consultation consultation dto.
     *
     * @param patinet the patinet
     * @param doctor  the doctor
     * @param hour    the hour
     * @param minute  the minute
     * @param date    the date
     * @return the consultation dto
     * @throws FieldException the field exception
     */
    ConsultationDTO postScheduleConsultation(AnimalDTO patinet, UserDTO doctor, String hour, String minute, Date date) throws FieldException;

    /**
     * Post update consultation consultation dto.
     *
     * @param id              the id
     * @param patinet         the patinet
     * @param doctor          the doctor
     * @param diagnostic      the diagnostic
     * @param details         the details
     * @param recommendations the recommendations
     * @param hour            the hour
     * @param minute          the minute
     * @param date            the date
     * @param status          the status
     * @return the consultation dto
     * @throws FieldException the field exception
     */
    ConsultationDTO postUpdateConsultation(int id, AnimalDTO patinet, UserDTO doctor, String diagnostic, String details, String recommendations, String hour, String minute, Date date, StatusDTO status) throws FieldException;

    /**
     * Delete consultation consultation dto.
     *
     * @param consultation the consultation
     * @return the consultation dto
     */
    ConsultationDTO deleteConsultation(ConsultationDTO consultation);
}
