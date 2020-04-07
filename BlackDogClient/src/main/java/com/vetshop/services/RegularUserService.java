package com.vetshop.services;

import com.vetshop.dtos.ConsultationDTO;
import com.vetshop.dtos.ConsultationsListWrapperDTO;
import com.vetshop.dtos.UserDTO;
import com.vetshop.dtos.UsersListWrapperDTO;
import org.graalvm.compiler.lir.LIRInstruction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RegularUserService {

    public List<UserDTO> findAllRegularUsers(){
        final String uri = "http://localhost:8080/getAllRegularUsers";

        RestTemplate restTemplate = new RestTemplate();

        UsersListWrapperDTO consultations = restTemplate.getForObject(uri, UsersListWrapperDTO.class);

        return consultations.getList();
    }

}
