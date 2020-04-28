package com.vetshop.services.implementations;

import com.vetshop.dtos.*;
import com.vetshop.entities.*;
import com.vetshop.notifications.NotificationService;
import com.vetshop.repositories.*;
import com.vetshop.services.ConsultationService;
import com.vetshop.services.exceptions.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Consultation service.
 */
@Service
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationRepository consultationRepo;

    private final AnimalRepository animalRepository;

    private final RegularUserRepository regularUserRepository;

    private final GearRepository gearRepository;

    private final ItemRepository itemRepository;

    private final NotificationService notificationService;

    /**
     * Instantiates a new Consultation service.
     *
     * @param consultationRepo      the consultation repo
     * @param animalRepository      the animal repository
     * @param regularUserRepository the regular user repository
     * @param notificationService   the notification service
     * @param gearRepository        the gear repository
     * @param itemRepository        the item repository
     */
    @Autowired
    public ConsultationServiceImpl(ConsultationRepository consultationRepo, AnimalRepository animalRepository, RegularUserRepository regularUserRepository, NotificationService notificationService, GearRepository gearRepository, ItemRepository itemRepository) {
        this.consultationRepo = consultationRepo;
        this.animalRepository = animalRepository;
        this.regularUserRepository = regularUserRepository;
        this.notificationService = notificationService;
        this.gearRepository = gearRepository;
        this.itemRepository = itemRepository;
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
    public ConsultationDTO delete(int id) throws NoSuchEntityException {
        Consultation consultation = consultationRepo.findById(id).orElse(null);
        if (consultation != null) {
            consultationRepo.delete(consultation);
        } else {
            throw new NoSuchEntityException("Consultation with given id doesn't exist");
        }
        return new ConsultationDTO(consultation);
    }

    @Override
    public List<ConsultationDTO> findAllForLoggedUser(RegularUserDTO regularUserDTO) {
        if (regularUserDTO.getUserType().equals(TypeDTO.REGULAR))
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
    public ConsultationDTO update(int consultationId, String patientId, String doctorId, String diagnostic, String details, String recommendations, Date date, StatusDTO status) {
        Animal animal = animalRepository.findById(Integer.parseInt(patientId)).orElse(null);
        RegularUser doctor = regularUserRepository.findById(Integer.parseInt(doctorId)).orElse(null);
        Consultation consultation = Consultation.builder().status(Status.valueOf(status.toString())).animal(animal).details(details).diagnostic(diagnostic).doctor(doctor).recommendations(recommendations).date(date).consultationId(consultationId).build();
        return new ConsultationDTO(consultationRepo.save(consultation));
    }

    @Override
    public ConsultationDTO schedule(String patientId, String doctorId, String diagnostic, String details, String recommendations, Date date, StatusDTO status, List<ItemDTO> neededGear) {
        Animal animal = animalRepository.findById(Integer.parseInt(patientId)).orElse(null);
        RegularUser doctor = regularUserRepository.findById(Integer.parseInt(doctorId)).orElse(null);
        Consultation consultation = Consultation.builder().animal(animal).neededGear(new ArrayList<>()).details(details).diagnostic(diagnostic).doctor(doctor).status(Status.valueOf(status.toString())).recommendations(recommendations).date(date).build();
        consultation = consultationRepo.save(consultation);
        for (ItemDTO i : neededGear) {
            Item item = itemRepository.findByName(i.getName());
            Gear g = Gear.builder().consultation(consultation).item(item).quantity(i.getQuantity()).build();
            item.addGear(g);
            consultation.addGear(g);
            gearRepository.save(g);
            itemRepository.save(item);
        }
        notificationService.updateSubjectByTopic(doctor.getUsername(), consultation);
        return new ConsultationDTO(consultationRepo.save(consultation));
    }

    @Override
    public ConsultationDTO begin(int consultationId) {
        Consultation consultation = consultationRepo.findById(consultationId).orElse(null);
        List<Gear> gear = gearRepository.getAllByConsultation(consultation);
        for (Gear g : gear) {
            if (g.getQuantity() > g.getItemStock()) {
                return null;
            }
        }
        for (Gear g : gear) {
            Item i = g.getItem();
            i.setStock(i.getStock() - g.getQuantity());
            itemRepository.save(i);
        }
        if (consultation != null) {
            consultation.setStatus(Status.IN_PROGRESS);
            return new ConsultationDTO(consultationRepo.save(consultation));
        }
        return null;
    }

    @Override
    public ConsultationDTO complete(int consultationId, String diagnostic, String details, String recommendations) {
        Consultation consultation = consultationRepo.findById(consultationId).orElse(null);
        if (consultation != null) {
            consultation.setStatus(Status.DONE);
            consultation.setDetails(details);
            consultation.setDiagnostic(diagnostic);
            consultation.setRecommendations(recommendations);

            return new ConsultationDTO(consultationRepo.save(consultation));
        }
        return null;
    }


}
