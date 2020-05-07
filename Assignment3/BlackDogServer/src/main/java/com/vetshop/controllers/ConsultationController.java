package com.vetshop.controllers;

import com.vetshop.dtos.ConsultationDTO;
import com.vetshop.dtos.ConsultationsListWrapperDTO;
import com.vetshop.dtos.RegularUserDTO;
import com.vetshop.services.ConsultationService;
import com.vetshop.services.exceptions.NoSuchEntityException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Consultation controller.
 */
@RestController
public class ConsultationController {

    private final ConsultationService consultationService;

    /**
     * Instantiates a new Consultation controller.
     *
     * @param consultationService the consultation service
     */
    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    /**
     * Gets consultations.
     *
     * @param regularUserDTO the regular user dto
     * @return the consultations
     */
    @PostMapping(value = "/getAllConsultationsForUser")
    public ConsultationsListWrapperDTO getConsultations(@RequestBody RegularUserDTO regularUserDTO) {
        return ConsultationsListWrapperDTO.builder().list(consultationService.findAllForLoggedUser(regularUserDTO)).build();
    }

    /**
     * Create consultation consultation dto.
     *
     * @param consultationDTO the consultation dto
     * @return the consultation dto
     */
    @PostMapping(value = "/createConsultation")
    public ConsultationDTO createConsultation(@RequestBody ConsultationDTO consultationDTO) {
        return consultationService.save(consultationDTO.getAnimalId() + "", consultationDTO.getDoctorId() + "", consultationDTO.getDiagnostic(), consultationDTO.getDetails(), consultationDTO.getRecommendations(), consultationDTO.getDate(), consultationDTO.getStatus());
    }

    /**
     * Schedule consultation consultation dto.
     *
     * @param consultationDTO the consultation dto
     * @return the consultation dto
     */
    @PostMapping(value = "/scheduleConsultation")
    public ConsultationDTO scheduleConsultation(@RequestBody ConsultationDTO consultationDTO) {
        return consultationService.schedule(consultationDTO.getAnimalId() + "", consultationDTO.getDoctorId() + "", consultationDTO.getDiagnostic(), consultationDTO.getDetails(), consultationDTO.getRecommendations(), consultationDTO.getDate(), consultationDTO.getStatus());
    }

    /**
     * Update consultation consultation dto.
     *
     * @param consultationDTO the consultation dto
     * @return the consultation dto
     */
    @PostMapping(value = "/updateConsultation")
    public ConsultationDTO updateConsultation(@RequestBody ConsultationDTO consultationDTO) {
        return consultationService.update(consultationDTO.getConsultationId(), consultationDTO.getAnimalId() + "", consultationDTO.getDoctorId() + "", consultationDTO.getDiagnostic(), consultationDTO.getDetails(), consultationDTO.getRecommendations(), consultationDTO.getDate(), consultationDTO.getStatus());
    }

    /**
     * Delete consultation consultation dto.
     *
     * @param consultationDTO the consultation dto
     * @return the consultation dto
     * @throws NoSuchEntityException the no such entity exception
     */
    @PostMapping(value = "/deleteConsultation")
    public ConsultationDTO deleteConsultation(@RequestBody ConsultationDTO consultationDTO) throws NoSuchEntityException {
        consultationService.delete(consultationDTO.getConsultationId());
        return consultationDTO;
    }
}
