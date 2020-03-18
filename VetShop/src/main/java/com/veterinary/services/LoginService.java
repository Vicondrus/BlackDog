package com.veterinary.services;

import com.veterinary.services.exceptions.InvalidCredentialsException;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    public void loginUser(String username, String password) throws InvalidCredentialsException;

}
