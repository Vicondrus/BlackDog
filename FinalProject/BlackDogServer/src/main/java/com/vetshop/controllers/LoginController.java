package com.vetshop.controllers;

import com.vetshop.dtos.RegularUserDTO;
import com.vetshop.dtos.TypeDTO;
import com.vetshop.services.LoginService;
import com.vetshop.services.exceptions.InvalidCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Login controller.
 */
@RestController
public class LoginController {

    private final LoginService loginService;

    /**
     * Instantiates a new Login controller.
     *
     * @param loginService the login service
     */
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Check login type dto.
     *
     * @param regularUserDTO the regular user dto
     * @return the type dto
     */
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
