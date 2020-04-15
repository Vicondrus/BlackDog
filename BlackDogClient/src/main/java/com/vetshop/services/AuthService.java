package com.vetshop.services;

import com.vetshop.dtos.UserDTO;
import com.vetshop.dtos.TypeDTO;
import com.vetshop.exceptions.InvalidCredentialsException;
import com.vetshop.notifications.NotificationWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import javax.inject.Inject;
import java.io.IOException;

@Component
public class AuthService {

    @Autowired
    private NotificationWebSocketHandler notificationWebSocketHandler;

    public TypeDTO postLogin(String username, String password) throws InvalidCredentialsException, IOException {
        final String uri = "http://localhost:8080/login";

        RestTemplate restTemplate = new RestTemplate();

        UserDTO regularUserDTO = UserDTO.builder().username(username).password(password).build();

        TypeDTO result = restTemplate.postForObject(uri, regularUserDTO, TypeDTO.class);

        if(result == null)
            throw new InvalidCredentialsException("Invalid credentials");

        Authentication auth = new UsernamePasswordAuthenticationToken(UserDTO.builder().userType(result).username(username).build(), null);
        SecurityContextHolder.getContext().setAuthentication(auth);

        notificationWebSocketHandler.sendMessage("START####"+username);

        return result;
    }

    public void logOut() throws IOException {
        UserDTO principal = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        notificationWebSocketHandler.sendMessage("STOP####"+principal.getUsername());

        SecurityContextHolder.getContext().setAuthentication(null);
    }

    public TypeDTO getUserType(){
        UserDTO principal = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUserType();
    }

}
