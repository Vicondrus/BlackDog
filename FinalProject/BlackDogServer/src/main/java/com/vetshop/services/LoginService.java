package com.vetshop.services;


import com.vetshop.dtos.TypeDTO;
import com.vetshop.services.exceptions.InvalidCredentialsException;
import org.springframework.stereotype.Service;

/**
 * The interface Login service.
 */
@Service
public interface LoginService {

    /**
     * Login user type dto.
     *
     * @param username the username
     * @param password the password
     * @return the type dto
     * @throws InvalidCredentialsException the invalid credentials exception
     */
    TypeDTO loginUser(String username, String password) throws InvalidCredentialsException;

}
