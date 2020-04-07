package com.vetshop.controllers;

import com.vetshop.dtos.ConsultationsListWrapperDTO;
import com.vetshop.dtos.RegularUserDTO;
import com.vetshop.services.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @PostMapping(value = "/getAllControllersForUser")
    public ConsultationsListWrapperDTO checkLogin(@RequestBody RegularUserDTO regularUserDTO) {
        return ConsultationsListWrapperDTO.builder().list(consultationService.findAllForLoggedUser(regularUserDTO)).build();
    }
}
