package com.vetshop.services.implementations;

import com.vetshop.dtos.ConsultationDTO;
import com.vetshop.dtos.RegularUserDTO;
import com.vetshop.dtos.StatusDTO;
import com.vetshop.dtos.TypeDTO;
import com.vetshop.entities.*;
import com.vetshop.repositories.AnimalRepository;
import com.vetshop.repositories.ConsultationRepository;
import com.vetshop.repositories.RegularUserRepository;
import com.vetshop.services.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ConsultationDTO save(String patientId, String doctorId, String diagnostic, String details, String recommendations, String hour, String minute, Date date, StatusDTO status) {
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
        Consultation consultation = Consultation.builder().animal(animal).details(details).diagnostic(diagnostic).status(Status.valueOf(status.toString())).doctor(doctor).recommendations(recommendations).date(correctDate).build();
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

//    @Override
//    public Report reportConsultation(int id, String path, String type) throws IOException, DocumentException {
//        //Consultation cons = consultationRepo.findById(id).orElse(null);
//        //ReportFactory rf = new ReportFactory();
//        //Report report = rf.generateReport(ReportType.valueOf(type));
//        //report.generateReport(cons, path);
//        //return report;
//    }

    @Override
    public List<ConsultationDTO> findAllForLoggedUser(RegularUserDTO regularUserDTO) {
        if(regularUserDTO.getUserType().equals(TypeDTO.REGULAR))
            return consultationRepo.findByDoctorUsername(regularUserDTO.getUsername()).stream().map(ConsultationDTO::new).collect(Collectors.toList());
        else
            return consultationRepo.findAll().stream().map(ConsultationDTO::new).collect(Collectors.toList());
    }

    @Override
    public ConsultationDTO save(String patientId, String doctorId, String diagnostic, String details, String recommendations, Date date, StatusDTO status) {
        Animal animal = animalRepository.findById(Integer.parseInt(patientId)).orElse(null);
        RegularUser doctor = regularUserRepository.findById(Integer.parseInt(doctorId)).orElse(null);
        Consultation consultation = Consultation.builder().animal(animal).details(details).diagnostic(diagnostic).doctor(doctor).status(Status.valueOf(status.toString())).recommendations(recommendations).date(date).build();
        return new ConsultationDTO(consultationRepo.save(consultation));
    }

    @Override
    public ConsultationDTO update(int consultationId, String patientId, String doctorId, String diagnostic, String details, String recommendations, Date date) {
        Animal animal = animalRepository.findById(Integer.parseInt(patientId)).orElse(null);
        RegularUser doctor = regularUserRepository.findById(Integer.parseInt(doctorId)).orElse(null);
        Consultation consultation = Consultation.builder().animal(animal).details(details).diagnostic(diagnostic).doctor(doctor).recommendations(recommendations).date(date).consultationId(consultationId).build();
        return new ConsultationDTO(consultationRepo.save(consultation));
    }
}
