package com.vetshop.services.implementations;

import com.vetshop.dtos.TypeDTO;
import com.vetshop.entities.RegularUser;
import com.vetshop.entities.UserType;
import com.vetshop.repositories.UserRepository;
import com.vetshop.services.exceptions.AlreadyExistingException;
import com.vetshop.services.exceptions.InvalidCredentialsException;
import com.vetshop.util.ActiveUsersStore;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * The type Login service impl test.
 */
@RunWith(MockitoJUnitRunner.class)
class LoginServiceImplTest {

    private UserRepository userRepository;

    private LoginServiceImpl loginService;

    private ActiveUsersStore activeUsersStore;

    /**
     * On set up.
     */
    @BeforeEach
    public void onSetUp() {
        userRepository = Mockito.mock(UserRepository.class);
        activeUsersStore = Mockito.mock(ActiveUsersStore.class);
        loginService = new LoginServiceImpl(userRepository, activeUsersStore);
    }

    /**
     * Login regular user test.
     *
     * @throws InvalidCredentialsException the invalid credentials exception
     */
    @Test
    public void loginRegularUserTest() throws InvalidCredentialsException, AlreadyExistingException {

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

    /**
     * Login admin user test.
     *
     * @throws InvalidCredentialsException the invalid credentials exception
     */
    @Test
    public void loginAdminUserTest() throws InvalidCredentialsException, AlreadyExistingException {

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

    /**
     * Login user invalid credentials exception.
     *
     * @throws InvalidCredentialsException the invalid credentials exception
     */
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