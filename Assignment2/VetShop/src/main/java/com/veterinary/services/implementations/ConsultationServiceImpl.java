package com.veterinary.services.implementations;

import com.itextpdf.text.DocumentException;
import com.veterinary.dtos.ConsultationDTO;
import com.veterinary.dtos.RegularUserDTO;
import com.veterinary.entities.*;
import com.veterinary.reports.Report;
import com.veterinary.reports.ReportFactory;
import com.veterinary.reports.ReportType;
import com.veterinary.repositories.AnimalRepository;
import com.veterinary.repositories.ConsultationRepository;
import com.veterinary.repositories.RegularUserRepository;
import com.veterinary.services.AnimalService;
import com.veterinary.services.ConsultationService;
import com.veterinary.services.RegularUserService;
import com.veterinary.services.exceptions.FieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    private ConsultationRepository consultationRepo;

    private AnimalRepository animalRepository;

    private RegularUserRepository regularUserRepository;

    @Autowired
    public ConsultationServiceImpl(ConsultationRepository consultationRepo, AnimalRepository animalRepository, RegularUserRepository regularUserRepository){
        this.consultationRepo = consultationRepo;
        this.animalRepository = animalRepository;
        this.regularUserRepository = regularUserRepository;
    }

    @Override
    public List<ConsultationDTO> findAll() {
        return consultationRepo.findAll().stream().map(ConsultationDTO::new).collect(Collectors.toList());
    }

    @Override
    public ConsultationDTO save(String patientId, String doctorId, String diagnostic, String details, String recommendations, String hour, String minute, Date date) throws FieldException {
        Animal animal = animalRepository.findById(Integer.parseInt(patientId)).orElse(null);
        RegularUser doctor = regularUserRepository.findById(Integer.parseInt(doctorId)).orElse(null);
        Calendar cal = Calendar.getInstance(); // locale-specific
        cal.setTime(date);
        if (hour.equals("") || minute.equals(""))
            throw new FieldException("Hours and minutes cannot be empty");
        if (Integer.parseInt(hour) >= 24 || Integer.parseInt(hour) < 0)
            throw new FieldException("Hours must be between 00 and 23");
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        if (Integer.parseInt(minute) >= 60 || Integer.parseInt(minute)<0)
            throw new FieldException("Minutes must be between 00 and 59");
        cal.set(Calendar.MINUTE, Integer.parseInt(minute));
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long time = cal.getTimeInMillis();
        Date correctDate = new Date(time);
        Consultation consultation = Consultation.builder().animal(animal).details(details).diagnostic(diagnostic).doctor(doctor).recommendations(recommendations).date(correctDate).build();
        return new ConsultationDTO(consultationRepo.save(consultation));
    }

    @Override
    public ConsultationDTO update(int consultationId, String patientId, String doctorId, String diagnostic, String details, String recommendations, String hour, String minute, Date date) throws FieldException {
        Animal animal = animalRepository.findById(Integer.parseInt(patientId)).orElse(null);
        RegularUser doctor = regularUserRepository.findById(Integer.parseInt(doctorId)).orElse(null);
        Calendar cal = Calendar.getInstance(); // locale-specific
        cal.setTime(date);
        if (hour.equals("") || minute.equals(""))
            throw new FieldException("Hours and minutes cannot be empty");
        if (Integer.parseInt(hour) >= 24 || Integer.parseInt(hour) < 0)
            throw new FieldException("Hours must be between 00 and 23");
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        if (Integer.parseInt(minute) >= 60 || Integer.parseInt(minute)<0)
            throw new FieldException("Minutes must be between 00 and 59");
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
    public Report reportConsultation(int id, String path, String type) throws IOException, DocumentException {
        Consultation cons = consultationRepo.findById(id).orElse(null);
        ReportFactory rf = new ReportFactory();
        Report report = rf.generateReport(ReportType.valueOf(type));
        report.generateReport(cons, path);
        return report;
    }

    @Override
    public List<ConsultationDTO> findAllForLoggedUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal.getUserType().equals(UserType.REGULAR))
            return consultationRepo.findByDoctor((RegularUser) principal).stream().map(ConsultationDTO::new).collect(Collectors.toList());
        else
            return consultationRepo.findAll().stream().map(ConsultationDTO::new).collect(Collectors.toList());
    }
}
