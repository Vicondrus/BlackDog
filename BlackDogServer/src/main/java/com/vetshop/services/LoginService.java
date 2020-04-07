package com.vetshop.services;


import com.vetshop.dtos.TypeDTO;
import com.vetshop.services.exceptions.InvalidCredentialsException;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    TypeDTO loginUser(String username, String password) throws InvalidCredentialsException;

}
