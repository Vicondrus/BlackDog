package com.veterinary.services.implementations;

import com.veterinary.dtos.AnimalDTO;
import com.veterinary.dtos.ConsultationDTO;
import com.veterinary.dtos.RegularUserDTO;
import com.veterinary.entities.*;
import com.veterinary.repositories.AnimalRepository;
import com.veterinary.repositories.RegularUserRepository;
import com.veterinary.repositories.UserRepository;
import com.veterinary.services.exceptions.FieldException;
import com.veterinary.services.exceptions.NoSuchEntityException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimalServiceImplTest {

    private AnimalRepository animalRepository;

    private AnimalServiceImpl animalService;

    private RegularUserRepository regularUserRepository;

    @BeforeEach
    void setUp() {
        animalRepository = Mockito.mock(AnimalRepository.class);
        regularUserRepository = Mockito.mock(RegularUserRepository.class);
        animalService = new AnimalServiceImpl(animalRepository, regularUserRepository);
    }

    @Test
    void getAll() {
        List<AnimalDTO> list1 = new ArrayList<AnimalDTO>();
        List<Animal> list2 = new ArrayList<Animal>();
        for(int i = 0; i< 10; i++){
            Animal a = Animal.builder().animalId(i).consultationList(null).name(i+"").owner(i+"").species(i+"").build();
            list2.add(a);
            list1.add(new AnimalDTO(a));
        }

        Mockito.when(animalRepository.findAll()).thenReturn(list2);

        List<AnimalDTO> list3 = animalService.getAll();

        Assert.assertThat(list3, CoreMatchers.is(list1));
    }

    @Test
    void getAllAnimalsConsultedByTest() throws NoSuchEntityException {
        List<Animal> list1 = new ArrayList<Animal>();
        List<AnimalDTO> list2 = new ArrayList<AnimalDTO>();
        List<Consultation> q = new ArrayList<Consultation>();
        for(int i = 0; i< 10; i++){
            Animal a = Animal.builder().species(i+"").owner(i+"").name(i+"").build();
            list1.add(a);
            list2.add(new AnimalDTO(a));
            q.add(Consultation.builder().animal(a).build());
        }

        RegularUser ru = RegularUser.builder().consultations(q).idUser(1).build();

        Mockito.when(regularUserRepository.findById(1)).thenReturn(java.util.Optional.of(ru));

        List<AnimalDTO> list3 = animalService.getAllAnimalsConsultedBy(ru);

        Assert.assertThat(list3, CoreMatchers.is(list2));

    }

    @Test
    void getCorrespondingAnimalsAdminTest() throws NoSuchEntityException {
        User user = RegularUser.builder().idUser(1).userType(UserType.REGULAR).build();
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(user);

        List<Animal> list1 = new ArrayList<Animal>();
        List<AnimalDTO> list2 = new ArrayList<AnimalDTO>();
        List<Consultation> q = new ArrayList<Consultation>();
        for(int i = 0; i< 10; i++){
            Animal a = Animal.builder().species(i+"").owner(i+"").name(i+"").build();
            list1.add(a);
            list2.add(new AnimalDTO(a));
            q.add(Consultation.builder().animal(a).build());
        }

        RegularUser ru = RegularUser.builder().consultations(q).idUser(1).build();

        Mockito.when(regularUserRepository.findById(1)).thenReturn(java.util.Optional.of(ru));

        List<AnimalDTO> list3 = animalService.getCorrespondingAnimals();
        Assert.assertThat(list2, CoreMatchers.is(list3));
    }

    @Test
    void getCorrespondingAnimalsRegularTest() throws NoSuchEntityException {
        User user = RegularUser.builder().userType(UserType.ADMIN).build();
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(user);

        List<Animal> list2 = new ArrayList<Animal>();
        List<AnimalDTO> list3 = new ArrayList<AnimalDTO>();
        for(int i = 0; i< 10; i++){
            Animal a = Animal.builder().animalId(i).consultationList(null).name(i+"").owner(i+"").species(i+"").build();
            list2.add(a);
            list3.add(new AnimalDTO(a));
        }

        Mockito.when(animalRepository.findAll()).thenReturn(list2);

        List<AnimalDTO> list1 = animalService.getCorrespondingAnimals();
        Assert.assertThat(list1, CoreMatchers.is(list3));
    }

    @Test
    void saveTest() throws FieldException {
        Animal ru = Animal.builder().animalId(1).consultationList(null).name("animal").owner("animal").species("animal").build();

        Mockito.doAnswer(invocationOnMock -> {
            if(invocationOnMock.getArguments()[0] instanceof Animal)
                return ru;
            return null;
        }).when(animalRepository).save(Mockito.any(Animal.class));

        AnimalDTO check = new AnimalDTO(ru);

        AnimalDTO x = animalService.save("animal", "animal", "animal");
        Assert.assertTrue(new ReflectionEquals(check, "idAnimal").matches(x));
    }

    @Test
    void updateTest() throws FieldException {
        Animal ru = Animal.builder().animalId(1).consultationList(null).name("animal").owner("animal").species("animal").build();

        Mockito.doAnswer(invocationOnMock -> {
            if(invocationOnMock.getArguments()[0] instanceof Animal)
                return ru;
            return null;
        }).when(animalRepository).save(Mockito.any(Animal.class));

        AnimalDTO check = new AnimalDTO(ru);

        AnimalDTO x = animalService.save("animal", "animal", "animal");
        Assert.assertTrue(new ReflectionEquals(check, "idAnimal").matches(x));
    }

    @Test
    void removeByIdTest() {
        Animal ru = Animal.builder().animalId(1).consultationList(null).name("animal").owner("animal").species("animal").build();

        Mockito.when(animalRepository.findById(1)).thenReturn(java.util.Optional.of(ru));

        Mockito.doAnswer(invocationOnMock -> {
            if(invocationOnMock.getArguments()[0] instanceof Animal)
                return ru;
            return null;
        }).when(animalRepository).delete(Mockito.any(Animal.class));

        AnimalDTO check = new AnimalDTO(ru);

        AnimalDTO x = animalService.removeById(1);
        Assert.assertTrue(new ReflectionEquals(check).matches(x));
    }

    @Test
    void getByNameTest() {
        Animal ru = Animal.builder().animalId(1).consultationList(null).name("animal").owner("animal").species("animal").build();

        Mockito.when(animalRepository.findByName("animal")).thenReturn(ru);

        AnimalDTO check = new AnimalDTO(ru);

        AnimalDTO x = animalService.getByName("animal");
        Assert.assertTrue(new ReflectionEquals(check, "idAnimal").matches(x));
    }
}