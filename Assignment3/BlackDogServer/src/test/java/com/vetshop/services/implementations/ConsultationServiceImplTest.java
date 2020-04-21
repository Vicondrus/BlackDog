package com.vetshop.services.implementations;

import com.vetshop.dtos.ConsultationDTO;
import com.vetshop.dtos.RegularUserDTO;
import com.vetshop.dtos.StatusDTO;
import com.vetshop.entities.*;
import com.vetshop.notifications.NotificationService;
import com.vetshop.repositories.AnimalRepository;
import com.vetshop.repositories.ConsultationRepository;
import com.vetshop.repositories.RegularUserRepository;
import com.vetshop.services.exceptions.FieldException;
import com.vetshop.services.exceptions.NoSuchEntityException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Consultation service impl test.
 */
class ConsultationServiceImplTest {

    private ConsultationServiceImpl consultationService;

    private ConsultationRepository consultationRepo;

    private AnimalRepository animalRepository;

    private RegularUserRepository regularUserRepository;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        animalRepository = Mockito.mock(AnimalRepository.class);
        regularUserRepository = Mockito.mock(RegularUserRepository.class);
        consultationRepo = Mockito.mock(ConsultationRepository.class);
        NotificationService notificationService = Mockito.mock(NotificationService.class);
        consultationService = new ConsultationServiceImpl(consultationRepo, animalRepository, regularUserRepository, notificationService);
    }

    /**
     * Find all test.
     */
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

    /**
     * Save test.
     */
    @Test
    void saveTest(){

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

        ConsultationDTO x = consultationService.save("1", "1", "diagnostic", "details", "recommendations", "0", "0",new Date(), StatusDTO.DONE );
        Assert.assertTrue(new ReflectionEquals(check, "idAnimal").matches(x));
    }

    /**
     * Update test.
     *
     * @throws FieldException the field exception
     */
    @Test
    void updateTest() throws FieldException {

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

    /**
     * Delete test.
     */
    @Test
    void deleteTest() throws NoSuchEntityException {

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

    /**
     * Find all for logged user admin test.
     */
    @Test
    void findAllForLoggedUserAdminTest() {
        RegularUser user = RegularUser.builder().idUser(1).userType(UserType.ADMIN).build();

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

        List<ConsultationDTO> list3 = consultationService.findAllForLoggedUser(new RegularUserDTO(user));

        Assert.assertThat(list3, CoreMatchers.is(list1));
    }

    /**
     * Find all for logged user regular test.
     */
    @Test
    void findAllForLoggedUserRegularTest() {
        RegularUser user = RegularUser.builder().username("user").idUser(1).userType(UserType.REGULAR).build();

        List<ConsultationDTO> list1 = new ArrayList<ConsultationDTO>();
        List<Consultation> list2 = new ArrayList<Consultation>();
        for(int i = 0; i< 10; i++){
            Animal a = Animal.builder().animalId(1).name("a").build();

            RegularUser r = RegularUser.builder().idUser(1).username("u").build();

            Consultation ru = Consultation.builder().animal(a).date(null).details("details").diagnostic("diagnostic").doctor(r).recommendations("recommendation").build();
            list2.add(ru);
            list1.add(new ConsultationDTO(ru));
        }

        Mockito.when(consultationRepo.findByDoctorUsername("user")).thenReturn(list2);

        List<ConsultationDTO> list3 = consultationService.findAllForLoggedUser(new RegularUserDTO(user));

        Assert.assertThat(list3, CoreMatchers.is(list1));
    }
}