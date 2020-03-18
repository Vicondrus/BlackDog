package com.veterinary.services;

import com.veterinary.dtos.ConsultationDTO;
import com.veterinary.entities.Consultation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConsultationService {

    List<ConsultationDTO> findAll();

    ConsultationDTO save(Consultation consultation);

    ConsultationDTO update(Consultation consultation);

    ConsultationDTO delete(int id);

    List<ConsultationDTO> findAllForLoggedUser();

}
