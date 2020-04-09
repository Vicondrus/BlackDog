package com.vetshop.controllers;

import com.vetshop.dtos.*;
import com.vetshop.entities.UserType;
import com.vetshop.services.RegularUserService;
import com.vetshop.services.exceptions.AlreadyExistingException;
import com.vetshop.services.exceptions.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class RegularUserController {

    @Autowired
    private RegularUserService regularUserService;

    @GetMapping(value = "/getAllRegularUsers")
    public RegularUsersListWrapperDTO getAllRegularUsers() {
        return RegularUsersListWrapperDTO.builder().list(regularUserService.getAll().stream().filter(x -> x.getUserType().equals(TypeDTO.REGULAR)).collect(Collectors.toList())).build();
    }

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

    @PostMapping(value = "/createUser")
    public RegularUserDTO createUser(@RequestBody RegularUserDTO regularUserDTO) {
        RegularUserDTO created = null;
        try {
            created = regularUserService.save(regularUserDTO.getUsername(),regularUserDTO.getPassword(),regularUserDTO.getFullName(), regularUserDTO.getUserType().toString());
        } catch (AlreadyExistingException | NoSuchEntityException e) {
            e.printStackTrace();
        }
        return created;
    }

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
