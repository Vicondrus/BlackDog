package com.vetshop.services;

import com.vetshop.dtos.UserDTO;
import com.vetshop.dtos.TypeDTO;
import com.vetshop.exceptions.InvalidCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginService {

    public TypeDTO postLogin(String username, String password) throws InvalidCredentialsException {
        final String uri = "http://localhost:8080/login";

        RestTemplate restTemplate = new RestTemplate();

        UserDTO regularUserDTO = UserDTO.builder().username(username).password(password).build();

        TypeDTO result = restTemplate.postForObject(uri, regularUserDTO, TypeDTO.class);

        if(result == null)
            throw new InvalidCredentialsException("Invalid credentials");

        Authentication auth = new UsernamePasswordAuthenticationToken(UserDTO.builder().userType(result).username(username).build(), null);
        SecurityContextHolder.getContext().setAuthentication(auth);

        return result;
    }

}
