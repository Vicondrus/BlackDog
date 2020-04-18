package com.vetshop.services;

import com.vetshop.dtos.TypeDTO;
import com.vetshop.exceptions.InvalidCredentialsException;

import java.io.IOException;

/**
 * The interface Auth service.
 */
public interface AuthService {
    /**
     * Post login type dto.
     *
     * @param username the username
     * @param password the password
     * @return the type dto
     * @throws InvalidCredentialsException the invalid credentials exception
     * @throws IOException                 the io exception
     */
    TypeDTO postLogin(String username, String password) throws InvalidCredentialsException, IOException;

    /**
     * Log out.
     *
     * @throws IOException the io exception
     */
    void logOut() throws IOException;

    /**
     * Gets user type.
     *
     * @return the user type
     */
    TypeDTO getUserType();
}
