package com.vetshop.services.implementations;

import com.vetshop.dtos.AnimalDTO;
import com.vetshop.entities.Animal;
import com.vetshop.entities.Consultation;
import com.vetshop.entities.RegularUser;
import com.vetshop.repositories.AnimalRepository;
import com.vetshop.repositories.RegularUserRepository;
import com.vetshop.services.exceptions.FieldException;
import com.vetshop.services.exceptions.NoSuchEntityException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Animal service impl test.
 */
class AnimalServiceImplTest {

    private AnimalRepository animalRepository;

    private AnimalServiceImpl animalService;

    private RegularUserRepository regularUserRepository;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        animalRepository = Mockito.mock(AnimalRepository.class);
        regularUserRepository = Mockito.mock(RegularUserRepository.class);
        animalService = new AnimalServiceImpl(animalRepository, regularUserRepository);
    }

    /**
     * Gets all.
     */
    @Test
    void getAll() {
        List<AnimalDTO> list1 = new ArrayList<AnimalDTO>();
        List<Animal> list2 = new ArrayList<Animal>();
        for (int i = 0; i < 10; i++) {
            Animal a = Animal.builder().animalId(i).consultationList(null).name(i + "").owner(i + "").species(i + "").build();
            list2.add(a);
            list1.add(new AnimalDTO(a));
        }

        Mockito.when(animalRepository.findAll()).thenReturn(list2);

        List<AnimalDTO> list3 = animalService.getAll();

        Assert.assertThat(list3, CoreMatchers.is(list1));
    }

    /**
     * Gets all animals consulted by test.
     *
     * @throws NoSuchEntityException the no such entity exception
     */
    @Test
    void getAllAnimalsConsultedByTest() throws NoSuchEntityException {
        List<Animal> list1 = new ArrayList<Animal>();
        List<AnimalDTO> list2 = new ArrayList<AnimalDTO>();
        List<Consultation> q = new ArrayList<Consultation>();
        for (int i = 0; i < 10; i++) {
            Animal a = Animal.builder().species(i + "").owner(i + "").name(i + "").build();
            list1.add(a);
            list2.add(new AnimalDTO(a));
            q.add(Consultation.builder().animal(a).build());
        }

        RegularUser ru = RegularUser.builder().consultations(q).idUser(1).build();

        Mockito.when(regularUserRepository.findById(1)).thenReturn(java.util.Optional.of(ru));

        List<AnimalDTO> list3 = animalService.getAllAnimalsConsultedBy(ru);

        Assert.assertThat(list3, CoreMatchers.is(list2));

    }

    /**
     * Save test.
     *
     * @throws FieldException the field exception
     */
    @Test
    void saveTest() throws FieldException {
        Animal ru = Animal.builder().animalId(1).consultationList(null).name("animal").owner("animal").species("animal").build();

        Mockito.doAnswer(invocationOnMock -> {
            if (invocationOnMock.getArguments()[0] instanceof Animal)
                return ru;
            return null;
        }).when(animalRepository).save(Mockito.any(Animal.class));

        AnimalDTO check = new AnimalDTO(ru);

        AnimalDTO x = animalService.save("animal", "animal", "animal");
        Assert.assertTrue(new ReflectionEquals(check, "idAnimal").matches(x));
    }

    /**
     * Update test.
     *
     * @throws NoSuchEntityException the no such entity exception
     */
    @Test
    void updateTest() throws NoSuchEntityException {
        Animal ru = Animal.builder().animalId(1).consultationList(null).name("animal").owner("animal").species("animal").build();

        Mockito.doAnswer(invocationOnMock -> {
            if (invocationOnMock.getArguments()[0] instanceof Animal)
                return ru;
            return null;
        }).when(animalRepository).save(Mockito.any(Animal.class));

        Mockito.doAnswer(invocationOnMock -> {
            if (invocationOnMock.getArguments()[0] instanceof Integer)
                return Optional.of(ru);
            return null;
        }).when(animalRepository).findById(1);

        AnimalDTO check = new AnimalDTO(ru);

        AnimalDTO x = animalService.update(1, "animal", "animal", "animal");
        Assert.assertTrue(new ReflectionEquals(check, "idAnimal").matches(x));
    }

    /**
     * Remove by id test.
     */
    @Test
    void removeByIdTest() {
        Animal ru = Animal.builder().animalId(1).consultationList(null).name("animal").owner("animal").species("animal").build();

        Mockito.when(animalRepository.findById(1)).thenReturn(java.util.Optional.of(ru));

        Mockito.doAnswer(invocationOnMock -> {
            if (invocationOnMock.getArguments()[0] instanceof Animal)
                return ru;
            return null;
        }).when(animalRepository).delete(Mockito.any(Animal.class));

        AnimalDTO check = new AnimalDTO(ru);

        AnimalDTO x = animalService.removeById(1);
        Assert.assertTrue(new ReflectionEquals(check).matches(x));
    }

    /**
     * Gets by name test.
     */
    @Test
    void getByNameTest() {
        Animal ru = Animal.builder().animalId(1).consultationList(null).name("animal").owner("animal").species("animal").build();

        Mockito.when(animalRepository.findByName("animal")).thenReturn(ru);

        AnimalDTO check = new AnimalDTO(ru);

        AnimalDTO x = animalService.getByName("animal");
        Assert.assertTrue(new ReflectionEquals(check, "idAnimal").matches(x));
    }
}