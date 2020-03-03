package com.veterinary.services.implementations;

import com.veterinary.entities.Consultation;
import com.veterinary.repositories.ConsultationRepository;
import com.veterinary.services.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepo;

    @Override
    public List<Consultation> findAll() {
        return consultationRepo.findAll();
    }

    @Override
    public Consultation save(Consultation consultation) {
        return consultationRepo.save(consultation);
    }

    @Override
    public Consultation update(Consultation consultation) {
        return null;
    }

    @Override
    public Consultation delete(Consultation consultation) {
        return null;
    }
}
