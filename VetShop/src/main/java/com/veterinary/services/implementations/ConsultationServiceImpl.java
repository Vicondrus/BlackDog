package com.veterinary.services.implementations;

import com.veterinary.dtos.ConsultationDTO;
import com.veterinary.entities.Consultation;
import com.veterinary.entities.RegularUser;
import com.veterinary.entities.User;
import com.veterinary.repositories.ConsultationRepository;
import com.veterinary.services.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepo;

    @Override
    public List<ConsultationDTO> findAll() {
        return consultationRepo.findAll().stream().map(ConsultationDTO::new).collect(Collectors.toList());
    }

    @Override
    public ConsultationDTO save(Consultation consultation) {
        return new ConsultationDTO(consultationRepo.save(consultation));
    }

    @Override
    public ConsultationDTO update(Consultation consultation) {
        return null;
    }

    @Override
    public ConsultationDTO delete(int id) {
        Consultation consultation = consultationRepo.findById(id).orElse(null);
        consultationRepo.delete(consultation);
        return new ConsultationDTO(consultation);
    }

    @Override
    public List<ConsultationDTO> findAllForLoggedUser() {
        RegularUser principal = (RegularUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return consultationRepo.findByDoctor(principal).stream().map(ConsultationDTO::new).collect(Collectors.toList());
    }
}
