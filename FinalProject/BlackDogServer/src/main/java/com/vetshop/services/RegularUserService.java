package com.vetshop.services;

import com.vetshop.dtos.RegularUserDTO;
import com.vetshop.services.exceptions.AlreadyExistingException;
import com.vetshop.services.exceptions.NoSuchEntityException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The interface Regular user service.
 */
@Service
public interface RegularUserService {

    /**
     * Gets all.
     *
     * @return the all
     */
    List<RegularUserDTO> getAll();

    /**
     * Save regular user dto.
     *
     * @param username the username
     * @param password the password
     * @param fullName the full name
     * @param type     the type
     * @return the regular user dto
     * @throws AlreadyExistingException the already existing exception
     * @throws NoSuchEntityException    the no such entity exception
     */
    RegularUserDTO save(String username, String password, String fullName, String type) throws AlreadyExistingException, NoSuchEntityException;

    /**
     * Update regular user dto.
     *
     * @param id       the id
     * @param username the username
     * @param password the password
     * @param fullName the full name
     * @return the regular user dto
     * @throws NoSuchEntityException the no such entity exception
     */
    RegularUserDTO update(int id, String username, String password, String fullName) throws NoSuchEntityException;

    /**
     * Delete regular user dto.
     *
     * @param id the id
     * @return the regular user dto
     * @throws NoSuchEntityException the no such entity exception
     */
    RegularUserDTO delete(int id) throws NoSuchEntityException;

    /**
     * Gets by username.
     *
     * @param username the username
     * @return the by username
     * @throws NoSuchEntityException the no such entity exception
     */
    RegularUserDTO getByUsername(String username) throws NoSuchEntityException;

}
