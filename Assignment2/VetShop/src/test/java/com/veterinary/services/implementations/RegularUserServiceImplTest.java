package com.veterinary.services.implementations;

import com.veterinary.dtos.RegularUserDTO;
import com.veterinary.dtos.TypeDTO;
import com.veterinary.entities.RegularUser;
import com.veterinary.entities.UserType;
import com.veterinary.repositories.RegularUserRepository;
import com.veterinary.repositories.UserRepository;
import com.veterinary.services.exceptions.AlreadyExistingException;
import com.veterinary.services.exceptions.FieldException;
import com.veterinary.services.exceptions.InvalidCredentialsException;
import com.veterinary.services.exceptions.NoSuchEntityException;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class RegularUserServiceImplTest {


    private RegularUserRepository regularUserRepository;

    private RegularUserServiceImpl regularUserService;

    @BeforeEach
    public void onSetUp() {
        regularUserRepository = Mockito.mock(RegularUserRepository.class);
        regularUserService = new RegularUserServiceImpl(regularUserRepository);
    }

    @Test
    void getByUsernameTest() throws NoSuchEntityException {
        RegularUser ru = RegularUser.builder().build();
        ru.setFullName("user");
        ru.setPassword("user");
        ru.setUsername("user");
        ru.setUserType(UserType.REGULAR);
        ru.setIdUser(1);

        Mockito.when(regularUserRepository.findByUsername("user")).thenReturn(ru);

        RegularUserDTO check = RegularUserDTO.builder().username("user").build();

        RegularUserDTO x = regularUserService.getByUsername("user");
        Assert.assertTrue(new ReflectionEquals(check, "idUser", "password", "fullName", "type").matches(x));
    }

    @Test
    void getByUsernameTestNoSuchEntityException() {
        Mockito.when(regularUserRepository.findByUsername("user")).thenReturn(null);

        Assertions.assertThrows(NoSuchEntityException.class, () -> regularUserService.getByUsername("user"));
    }

    @Test
    void getAllTest() {
        List<RegularUserDTO> list1 = new ArrayList<RegularUserDTO>();
        List<RegularUser> list2 = new ArrayList<RegularUser>();
        for(int i = 0; i< 10; i++){
            RegularUser u = RegularUser.builder().fullName(i+"").idUser(i).password(i+"").username(i+"").userType(UserType.REGULAR).consultations(null).build();
            list2.add(u);
            list1.add(new RegularUserDTO(u));
        }

        Mockito.when(regularUserRepository.findAll()).thenReturn(list2);

        List<RegularUserDTO> list3 = regularUserService.getAll();

        Assert.assertThat(list3, CoreMatchers.is(list1));
    }

    @Test
    void saveTest() throws AlreadyExistingException, NoSuchEntityException, FieldException {
        RegularUser ru = RegularUser.builder().build();
        ru.setFullName("user");
        ru.setPassword("user");
        ru.setUsername("user");
        ru.setUserType(UserType.REGULAR);
        ru.setIdUser(1);

        Mockito.doAnswer(invocationOnMock -> {
            if(invocationOnMock.getArguments()[0] instanceof RegularUser)
                return ru;
            return null;
        }).when(regularUserRepository).save(Mockito.any(RegularUser.class));

        RegularUserDTO check = new RegularUserDTO(ru);

        RegularUserDTO x = regularUserService.save("user", "user", "user", "REGULAR");
        Assert.assertTrue(new ReflectionEquals(check, "idUser").matches(x));
    }

    @Test
    void saveTestAlreadyExistingException() throws AlreadyExistingException, NoSuchEntityException {
        RegularUser ru = RegularUser.builder().build();
        ru.setFullName("user");
        ru.setPassword("user");
        ru.setUsername("user");
        ru.setUserType(UserType.REGULAR);
        ru.setIdUser(1);

        Mockito.doAnswer(invocationOnMock -> ru).when(regularUserRepository).findByUsername("user");

        Assertions.assertThrows(AlreadyExistingException.class, () -> regularUserService.save("user", "user", "user", "REGULAR"));
    }


    @Test
    void updateTest() throws NoSuchEntityException, FieldException {
        RegularUser ru = RegularUser.builder().build();
        ru.setFullName("user");
        ru.setPassword("user");
        ru.setUsername("user");
        ru.setUserType(UserType.REGULAR);
        ru.setIdUser(1);

        Mockito.when(regularUserRepository.findById(1)).thenReturn(java.util.Optional.of(ru));

        Mockito.doAnswer(invocationOnMock -> {
            if(invocationOnMock.getArguments()[0] instanceof RegularUser)
                return ru;
            return null;
        }).when(regularUserRepository).save(Mockito.any(RegularUser.class));

        RegularUserDTO check = new RegularUserDTO(ru);

        RegularUserDTO x = regularUserService.update(1, "user", "user", "user");
        Assert.assertTrue(new ReflectionEquals(check).matches(x));
    }

    @Test
    void updateTestNoSuchEntityException() {

        Mockito.when(regularUserRepository.findById(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchEntityException.class, () -> regularUserService.update(1, "user", "user", "user"));
    }

    @Test
    void deleteTest() throws NoSuchEntityException {
        RegularUser ru = RegularUser.builder().build();
        ru.setFullName("user");
        ru.setPassword("user");
        ru.setUsername("user");
        ru.setUserType(UserType.REGULAR);
        ru.setIdUser(1);

        Mockito.when(regularUserRepository.findById(1)).thenReturn(java.util.Optional.of(ru));

        Mockito.doAnswer(invocationOnMock -> {
            if(invocationOnMock.getArguments()[0] instanceof RegularUser)
                return ru;
            return null;
        }).when(regularUserRepository).delete(Mockito.any(RegularUser.class));

        RegularUserDTO check = new RegularUserDTO(ru);

        RegularUserDTO x = regularUserService.delete(1);
        Assert.assertTrue(new ReflectionEquals(check).matches(x));
    }

    @Test
    void deleteTestNoSuchEntityException() {

        Mockito.when(regularUserRepository.findById(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchEntityException.class, () -> regularUserService.delete(1));
    }
}