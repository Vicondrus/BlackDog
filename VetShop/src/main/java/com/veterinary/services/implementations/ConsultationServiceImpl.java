package com.veterinary.services.implementations;

import com.veterinary.dtos.ConsultationDTO;
import com.veterinary.dtos.RegularUserDTO;
import com.veterinary.entities.Animal;
import com.veterinary.entities.Consultation;
import com.veterinary.entities.RegularUser;
import com.veterinary.entities.User;
import com.veterinary.repositories.AnimalRepository;
import com.veterinary.repositories.ConsultationRepository;
import com.veterinary.repositories.RegularUserRepository;
import com.veterinary.services.AnimalService;
import com.veterinary.services.ConsultationService;
import com.veterinary.services.RegularUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepo;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private RegularUserRepository regularUserRepository;

    @Override
    public List<ConsultationDTO> findAll() {
        return consultationRepo.findAll().stream().map(ConsultationDTO::new).collect(Collectors.toList());
    }

    @Override
    public ConsultationDTO save(String patientId, String doctorId, String diagnostic, String details, String recommendations, String hour, String minute, Date date) {
        Animal animal = animalRepository.findById(Integer.parseInt(patientId)).orElse(null);
        RegularUser doctor = regularUserRepository.findById(Integer.parseInt(doctorId)).orElse(null);
        Calendar cal = Calendar.getInstance(); // locale-specific
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        cal.set(Calendar.MINUTE, Integer.parseInt(minute));
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long time = cal.getTimeInMillis();
        Date correctDate = new Date(time);
        Consultation consultation = Consultation.builder().animal(animal).details(details).diagnostic(diagnostic).doctor(doctor).recommendations(recommendations).date(correctDate).build();
        return new ConsultationDTO(consultationRepo.save(consultation));
    }

    @Override
    public ConsultationDTO update(int consultationId, String patientId, String doctorId, String diagnostic, String details, String recommendations, String hour, String minute, Date date) {
        Animal animal = animalRepository.findById(Integer.parseInt(patientId)).orElse(null);
        RegularUser doctor = regularUserRepository.findById(Integer.parseInt(doctorId)).orElse(null);
        Calendar cal = Calendar.getInstance(); // locale-specific
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        cal.set(Calendar.MINUTE, Integer.parseInt(minute));
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long time = cal.getTimeInMillis();
        Date correctDate = new Date(time);
        Consultation consultation = Consultation.builder().animal(animal).details(details).diagnostic(diagnostic).doctor(doctor).recommendations(recommendations).date(correctDate).consultationId(consultationId).build();
        return new ConsultationDTO(consultationRepo.save(consultation));
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
