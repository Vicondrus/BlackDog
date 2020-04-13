package com.vetshop.controllers;

import com.vetshop.dtos.ConsultationDTO;
import com.vetshop.dtos.ConsultationsListWrapperDTO;
import com.vetshop.dtos.RegularUserDTO;
import com.vetshop.services.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @PostMapping(value = "/getAllConsultationsForUser")
    public ConsultationsListWrapperDTO getConsultations(@RequestBody RegularUserDTO regularUserDTO) {
        return ConsultationsListWrapperDTO.builder().list(consultationService.findAllForLoggedUser(regularUserDTO)).build();
    }

    @PostMapping(value = "/createConsultation")
    public ConsultationDTO createConsultation(@RequestBody ConsultationDTO consultationDTO){
        return consultationService.save(consultationDTO.getAnimalId()+"", consultationDTO.getDoctorId()+"", consultationDTO.getDiagnostic(), consultationDTO.getDetails(), consultationDTO.getRecommendations(), consultationDTO.getDate(), consultationDTO.getStatus());
    }

    @PostMapping(value = "/scheduleConsultation")
    public ConsultationDTO scheduleConsultation(@RequestBody ConsultationDTO consultationDTO){
        return consultationService.save(consultationDTO.getAnimalId()+"", consultationDTO.getDoctorId()+"", consultationDTO.getDiagnostic(), consultationDTO.getDetails(), consultationDTO.getRecommendations(), consultationDTO.getDate(), consultationDTO.getStatus());
    }

    @PostMapping(value = "/updateConsultation")
    public ConsultationDTO updateConsultation(@RequestBody ConsultationDTO consultationDTO){
        return consultationService.update(consultationDTO.getConsultationId(),consultationDTO.getAnimalId()+"", consultationDTO.getDoctorId()+"", consultationDTO.getDiagnostic(), consultationDTO.getDetails(), consultationDTO.getRecommendations(), consultationDTO.getDate(), consultationDTO.getStatus());
    }

    @PostMapping(value = "/deleteConsultation")
    public ConsultationDTO deleteConsultation(@RequestBody ConsultationDTO consultationDTO){
        consultationService.delete(consultationDTO.getConsultationId());
        return consultationDTO;
    }
}
