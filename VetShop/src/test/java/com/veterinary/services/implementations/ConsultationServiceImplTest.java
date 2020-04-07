package com.veterinary.services.implementations;

import com.itextpdf.text.DocumentException;
import com.veterinary.dtos.AnimalDTO;
import com.veterinary.dtos.ConsultationDTO;
import com.veterinary.entities.*;
import com.veterinary.reports.PdfReport;
import com.veterinary.reports.Report;
import com.veterinary.repositories.AnimalRepository;
import com.veterinary.repositories.ConsultationRepository;
import com.veterinary.repositories.RegularUserRepository;
import com.veterinary.services.ConsultationService;
import com.veterinary.services.exceptions.InvalidCredentialsException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConsultationServiceImplTest {

    private ConsultationServiceImpl consultationService;

    private ConsultationRepository consultationRepo;

    private AnimalRepository animalRepository;

    private RegularUserRepository regularUserRepository;

    @BeforeEach
    void setUp() {
        animalRepository = Mockito.mock(AnimalRepository.class);
        regularUserRepository = Mockito.mock(RegularUserRepository.class);
        consultationRepo = Mockito.mock(ConsultationRepository.class);
        consultationService = new ConsultationServiceImpl(consultationRepo, animalRepository, regularUserRepository);
    }

    @Test
    void findAllTest() {
        List<ConsultationDTO> list1 = new ArrayList<ConsultationDTO>();
        List<Consultation> list2 = new ArrayList<Consultation>();
        for(int i = 0; i< 10; i++){
            Animal a = Animal.builder().animalId(1).name("a").build();

            RegularUser r = RegularUser.builder().idUser(1).username("u").build();

            Consultation ru = Consultation.builder().animal(a).date(null).details("details").diagnostic("diagnostic").doctor(r).recommendations("recommendation").build();
            list2.add(ru);
            list1.add(new ConsultationDTO(ru));
        }

        Mockito.when(consultationRepo.findAll()).thenReturn(list2);

        List<ConsultationDTO> list3 = consultationService.findAll();

        Assert.assertThat(list3, CoreMatchers.is(list1));
    }

    @Test
    void saveTest() {

        Animal a = Animal.builder().animalId(1).name("a").build();

        RegularUser r = RegularUser.builder().idUser(1).username("u").build();

        Consultation ru = Consultation.builder().animal(a).date(null).details("details").diagnostic("diagnostic").doctor(r).recommendations("recommendation").build();

        Mockito.doAnswer(invocationOnMock -> {
            if(invocationOnMock.getArguments()[0] instanceof Consultation)
                return ru;
            return null;
        }).when(consultationRepo).save(Mockito.any(Consultation.class));

        Mockito.when(animalRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(a));

        Mockito.when(regularUserRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(r));

        ConsultationDTO check = new ConsultationDTO(ru);

        ConsultationDTO x = consultationService.save("1", "1", "diagnostic", "details", "recommendations", "0", "0", new Date());
        Assert.assertTrue(new ReflectionEquals(check, "idAnimal").matches(x));
    }

    @Test
    void updateTest() {

        Animal a = Animal.builder().animalId(1).name("a").build();

        RegularUser r = RegularUser.builder().idUser(1).username("u").build();

        Consultation ru = Consultation.builder().consultationId(1).animal(a).date(null).details("details").diagnostic("diagnostic").doctor(r).recommendations("recommendation").build();

        Mockito.doAnswer(invocationOnMock -> {
            if(invocationOnMock.getArguments()[0] instanceof Consultation)
                return ru;
            return null;
        }).when(consultationRepo).save(Mockito.any(Consultation.class));

        Mockito.when(animalRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(a));

        Mockito.when(regularUserRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(r));

        ConsultationDTO check = new ConsultationDTO(ru);

        ConsultationDTO x = consultationService.update(1, "1", "1", "diagnostic", "details", "recommendations", "0", "0", new Date());
        Assert.assertTrue(new ReflectionEquals(check, "idAnimal").matches(x));
    }

    @Test
    void deleteTest() {

        Animal a = Animal.builder().animalId(1).name("a").build();

        RegularUser r = RegularUser.builder().idUser(1).username("u").build();

        Consultation ru = Consultation.builder().consultationId(1).animal(a).date(null).details("details").diagnostic("diagnostic").doctor(r).recommendations("recommendation").build();

        Mockito.doAnswer(invocationOnMock -> {
            if(invocationOnMock.getArguments()[0] instanceof Consultation)
                return ru;
            return null;
        }).when(consultationRepo).delete(Mockito.any(Consultation.class));

        Mockito.when(consultationRepo.findById(1)).thenReturn(java.util.Optional.ofNullable(ru));

        Mockito.when(animalRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(a));

        Mockito.when(regularUserRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(r));

        ConsultationDTO check = new ConsultationDTO(ru);

        ConsultationDTO x = consultationService.delete(1);
        Assert.assertTrue(new ReflectionEquals(check, "idAnimal").matches(x));
    }

    @Test
    void reportConsultationTestIOException() throws IOException, DocumentException {
        Animal a = Animal.builder().animalId(1).name("a").build();

        RegularUser r = RegularUser.builder().idUser(1).username("u").build();

        Consultation ru = Consultation.builder().consultationId(1).animal(a).date(new Date()).details("details").diagnostic("diagnostic").doctor(r).recommendations("recommendation").build();

        Mockito.when(consultationRepo.findById(1)).thenReturn(java.util.Optional.ofNullable(ru));

        Report q = consultationService.reportConsultation(1,"lol","PDF");

        Report q1 = new PdfReport();

        //Assertions.assertThrows(IOException.class, () -> consultationService.reportConsultation(1,"lol", "PDF"));

        Assert.assertTrue(new ReflectionEquals(q1).matches(q));
    }

    @Test
    void findAllForLoggedUserAdminTest() {
        User user = RegularUser.builder().idUser(1).userType(UserType.ADMIN).build();
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(user);

        List<ConsultationDTO> list1 = new ArrayList<ConsultationDTO>();
        List<Consultation> list2 = new ArrayList<Consultation>();
        for(int i = 0; i< 10; i++){
            Animal a = Animal.builder().animalId(1).name("a").build();

            RegularUser r = RegularUser.builder().idUser(1).username("u").build();

            Consultation ru = Consultation.builder().animal(a).date(null).details("details").diagnostic("diagnostic").doctor(r).recommendations("recommendation").build();
            list2.add(ru);
            list1.add(new ConsultationDTO(ru));
        }

        Mockito.when(consultationRepo.findAll()).thenReturn(list2);

        List<ConsultationDTO> list3 = consultationService.findAllForLoggedUser();

        Assert.assertThat(list3, CoreMatchers.is(list1));
    }

    @Test
    void findAllForLoggedUserRegularTest() {
        User user = RegularUser.builder().idUser(1).userType(UserType.REGULAR).build();
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(user);

        List<ConsultationDTO> list1 = new ArrayList<ConsultationDTO>();
        List<Consultation> list2 = new ArrayList<Consultation>();
        for(int i = 0; i< 10; i++){
            Animal a = Animal.builder().animalId(1).name("a").build();

            RegularUser r = RegularUser.builder().idUser(1).username("u").build();

            Consultation ru = Consultation.builder().animal(a).date(null).details("details").diagnostic("diagnostic").doctor(r).recommendations("recommendation").build();
            list2.add(ru);
            list1.add(new ConsultationDTO(ru));
        }

        Mockito.doAnswer(invocationOnMock -> {
            if(invocationOnMock.getArguments()[0] instanceof RegularUser)
                return list2;
            return null;
        }).when(consultationRepo).findByDoctor(Mockito.any(RegularUser.class));

        List<ConsultationDTO> list3 = consultationService.findAllForLoggedUser();

        Assert.assertThat(list3, CoreMatchers.is(list1));
    }
}