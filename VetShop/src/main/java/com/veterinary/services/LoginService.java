package com.veterinary.services;

import com.veterinary.dtos.TypeDTO;
import com.veterinary.entities.UserType;
import com.veterinary.services.exceptions.InvalidCredentialsException;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    TypeDTO loginUser(String username, String password) throws InvalidCredentialsException;

}
