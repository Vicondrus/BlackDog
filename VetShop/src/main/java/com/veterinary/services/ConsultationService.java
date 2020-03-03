package com.veterinary.services;

import com.veterinary.entities.Consultation;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConsultationService {

    List<Consultation> findAll();

    Consultation save(Consultation consultation);

    Consultation update(Consultation consultation);

    Consultation delete(Consultation consultation);

}
