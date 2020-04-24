package com.vetshop.controllers;

import com.vetshop.dtos.RegularUserDTO;
import com.vetshop.dtos.RegularUsersListWrapperDTO;
import com.vetshop.dtos.TypeDTO;
import com.vetshop.services.RegularUserService;
import com.vetshop.services.exceptions.AlreadyExistingException;
import com.vetshop.services.exceptions.NoSuchEntityException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * The type Regular user controller.
 */
@RestController
public class RegularUserController {

    private final RegularUserService regularUserService;

    /**
     * Instantiates a new Regular user controller.
     *
     * @param regularUserService the regular user service
     */
    public RegularUserController(RegularUserService regularUserService) {
        this.regularUserService = regularUserService;
    }

    /**
     * Gets all regular users.
     *
     * @return the all regular users
     */
    @GetMapping(value = "/getAllRegularUsers")
    public RegularUsersListWrapperDTO getAllRegularUsers() {
        return RegularUsersListWrapperDTO.builder().list(regularUserService.getAll().stream().filter(x -> x.getUserType().equals(TypeDTO.REGULAR)).collect(Collectors.toList())).build();
    }

    /**
     * Delete user regular user dto.
     *
     * @param regularUserDTO the regular user dto
     * @return the regular user dto
     */
    @PostMapping(value = "/deleteUser")
    public RegularUserDTO deleteUser(@RequestBody RegularUserDTO regularUserDTO) {
        RegularUserDTO deleted = null;
        try {
            deleted = regularUserService.delete(regularUserDTO.getIdUser());
        } catch (NoSuchEntityException e) {
            e.printStackTrace();
        }
        return deleted;
    }

    /**
     * Create user regular user dto.
     *
     * @param regularUserDTO the regular user dto
     * @return the regular user dto
     */
    @PostMapping(value = "/createUser")
    public RegularUserDTO createUser(@RequestBody RegularUserDTO regularUserDTO) {
        RegularUserDTO created = null;
        try {
            created = regularUserService.save(regularUserDTO.getUsername(), regularUserDTO.getPassword(), regularUserDTO.getFullName(), regularUserDTO.getUserType().toString());
        } catch (AlreadyExistingException | NoSuchEntityException e) {
            e.printStackTrace();
        }
        return created;
    }

    /**
     * Update user regular user dto.
     *
     * @param regularUserDTO the regular user dto
     * @return the regular user dto
     */
    @PostMapping(value = "/updateUser")
    public RegularUserDTO updateUser(@RequestBody RegularUserDTO regularUserDTO) {
        RegularUserDTO created = null;
        try {
            created = regularUserService.update(regularUserDTO.getIdUser(), regularUserDTO.getUsername(), regularUserDTO.getPassword(), regularUserDTO.getFullName());
        } catch (NoSuchEntityException e) {
            e.printStackTrace();
        }
        return created;
    }
}
