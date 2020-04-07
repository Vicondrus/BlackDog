package com.vetshop.services;

import com.vetshop.dtos.ConsultationDTO;
import com.vetshop.dtos.ConsultationsListWrapperDTO;
import com.vetshop.dtos.UserDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ConsultationService {

    public List<ConsultationDTO> findAllForLoggedUser(){
        final String uri = "http://localhost:8080/getAllControllersForUser";

        RestTemplate restTemplate = new RestTemplate();

        UserDTO principal = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ConsultationsListWrapperDTO consultations = restTemplate.postForObject(uri, principal, ConsultationsListWrapperDTO.class);

        return consultations.getList();
    }

}
