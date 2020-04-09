package com.vetshop.services;

import com.vetshop.dtos.*;
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

    public UserDTO deleteUser(UserDTO userDTO){
        final String uri = "http://localhost:8080/deleteUser";

        RestTemplate restTemplate = new RestTemplate();
        userDTO = restTemplate.postForObject(uri, userDTO, UserDTO.class);
        return userDTO;
    }

    public UserDTO postCreateUser(String username, String password, String fullName){
        final String uri = "http://localhost:8080/createUser";

        RestTemplate restTemplate = new RestTemplate();

        UserDTO user = UserDTO.builder().username(username).userType(TypeDTO.REGULAR).password(password).fullName(fullName).build();

        user = restTemplate.postForObject(uri, user, UserDTO.class);

        return user;
    }

    public UserDTO postUpdateUser(int id, String username, String password, String fullName){
        final String uri = "http://localhost:8080/updateUser";

        RestTemplate restTemplate = new RestTemplate();

        UserDTO user = UserDTO.builder().username(username).idUser(id).userType(TypeDTO.REGULAR).password(password).fullName(fullName).build();

        user = restTemplate.postForObject(uri, user, UserDTO.class);

        return user;
    }

}
