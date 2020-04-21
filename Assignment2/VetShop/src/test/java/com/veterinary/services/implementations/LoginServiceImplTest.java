package com.veterinary.services.implementations;

import com.veterinary.dtos.TypeDTO;
import com.veterinary.entities.RegularUser;
import com.veterinary.entities.UserType;
import com.veterinary.repositories.UserRepository;
import com.veterinary.services.exceptions.InvalidCredentialsException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

@RunWith(MockitoJUnitRunner.class)
class LoginServiceImplTest {

    private UserRepository userRepository;

    private LoginServiceImpl loginService;

    @BeforeEach
    public void onSetUp() {
        userRepository = Mockito.mock(UserRepository.class);
        loginService = new LoginServiceImpl(userRepository);
    }

    @Test
    public void loginRegularUserTest() throws InvalidCredentialsException {

        RegularUser ru = RegularUser.builder().build();
        ru.setFullName("user");
        ru.setPassword("user");
        ru.setUsername("user");
        ru.setUserType(UserType.REGULAR);
        ru.setIdUser(1);
        Mockito.when(userRepository.findByUsername("user")).thenReturn( ru );

        TypeDTO ut = loginService.loginUser("user", "user");
        Assert.assertEquals(ut, TypeDTO.REGULAR);
    }

    @Test
    public void loginAdminUserTest() throws InvalidCredentialsException {

        RegularUser ru = RegularUser.builder().build();
        ru.setFullName("admin");
        ru.setPassword("admin");
        ru.setUsername("admin");
        ru.setUserType(UserType.ADMIN);
        ru.setIdUser(1);
        Mockito.when(userRepository.findByUsername("admin")).thenReturn(ru);

        TypeDTO ut = loginService.loginUser("admin", "admin");
        Assert.assertEquals(ut, TypeDTO.ADMIN);
    }

    @Test
    public void loginUserInvalidCredentialsException() throws InvalidCredentialsException {

        RegularUser ru = RegularUser.builder().build();
        ru.setFullName("user");
        ru.setPassword("password1");
        ru.setUsername("user");
        ru.setUserType(UserType.REGULAR);
        ru.setIdUser(1);
        Mockito.when(userRepository.findByUsername("user")).thenReturn( ru );

        Assertions.assertThrows(InvalidCredentialsException.class, () -> loginService.loginUser("user", "password"));
    }
}