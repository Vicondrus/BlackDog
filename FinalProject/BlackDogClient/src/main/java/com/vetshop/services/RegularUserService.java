package com.vetshop.services;

import com.vetshop.dtos.UserDTO;

import java.util.List;

/**
 * The interface Regular user service.
 */
public interface RegularUserService {
    /**
     * Find all regular users list.
     *
     * @return the list
     */
    List<UserDTO> findAllRegularUsers();

    /**
     * Delete user user dto.
     *
     * @param userDTO the user dto
     * @return the user dto
     */
    UserDTO deleteUser(UserDTO userDTO);

    /**
     * Post create user user dto.
     *
     * @param username the username
     * @param password the password
     * @param fullName the full name
     * @return the user dto
     */
    UserDTO postCreateUser(String username, String password, String fullName);

    /**
     * Post update user user dto.
     *
     * @param id       the id
     * @param username the username
     * @param password the password
     * @param fullName the full name
     * @return the user dto
     */
    UserDTO postUpdateUser(int id, String username, String password, String fullName);
}
