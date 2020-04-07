package com.vetshop.controllers;

import com.vetshop.dtos.ConsultationsListWrapperDTO;
import com.vetshop.dtos.RegularUserDTO;
import com.vetshop.dtos.RegularUsersListWrapperDTO;
import com.vetshop.dtos.TypeDTO;
import com.vetshop.services.RegularUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class RegularUserController {

    @Autowired
    private RegularUserService regularUserService;

    @PostMapping(value = "/getAllRegularUsers")
    public RegularUsersListWrapperDTO checkLogin() {
        return RegularUsersListWrapperDTO.builder().list(regularUserService.getAll().stream().filter(x -> x.getUserType().equals(TypeDTO.REGULAR)).collect(Collectors.toList())).build();
    }
}
