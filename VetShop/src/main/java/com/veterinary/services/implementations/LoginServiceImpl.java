package com.veterinary.services.implementations;

import com.veterinary.entities.User;
import com.veterinary.repositories.UserRepository;
import com.veterinary.services.LoginService;
import com.veterinary.services.exceptions.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void loginUser(String username, String password) throws InvalidCredentialsException {
        User user = userRepository.findByUsername(username);
        if(user.getPassword().equals(password)){
            Authentication auth = new UsernamePasswordAuthenticationToken(user, null);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }else{
            throw new InvalidCredentialsException("Wrong Password or Username");
        }
    }
}
