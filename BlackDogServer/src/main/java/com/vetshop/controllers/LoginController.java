package com.vetshop.controllers;

import com.vetshop.dtos.RegularUserDTO;
import com.vetshop.dtos.TypeDTO;
import com.vetshop.entities.RegularUser;
import com.vetshop.repositories.RegularUserRepository;
import com.vetshop.services.LoginService;
import com.vetshop.services.exceptions.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/login")
    public TypeDTO checkLogin(@RequestBody RegularUserDTO regularUserDTO) {
        TypeDTO type = null;
        try {
            type = loginService.loginUser(regularUserDTO.getUsername(), regularUserDTO.getPassword());
        } catch (InvalidCredentialsException e) {
            e.printStackTrace();
        }
        return type;
    }
}
