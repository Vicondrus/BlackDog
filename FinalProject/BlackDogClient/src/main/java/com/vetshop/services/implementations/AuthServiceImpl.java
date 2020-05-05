package com.vetshop.services.implementations;

import com.vetshop.dtos.TypeDTO;
import com.vetshop.dtos.UserDTO;
import com.vetshop.exceptions.InvalidCredentialsException;
import com.vetshop.notifications.NotificationWebSocketHandler;
import com.vetshop.services.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * The type Auth service.
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final NotificationWebSocketHandler notificationWebSocketHandler;

    private final String uriRoot;

    /**
     * Instantiates a new Auth service.
     *
     * @param notificationWebSocketHandler the notification web socket handler
     * @param hostAndPort                  the host and port
     */
    public AuthServiceImpl(NotificationWebSocketHandler notificationWebSocketHandler, @Value("${server.host-and-port}") String hostAndPort) {
        this.notificationWebSocketHandler = notificationWebSocketHandler;
        this.uriRoot = "http://" + hostAndPort;
    }

    @Override
    public TypeDTO postLogin(String username, String password) throws InvalidCredentialsException, IOException {
        final String uri = uriRoot + "/login";

        RestTemplate restTemplate = new RestTemplate();

        UserDTO regularUserDTO = UserDTO.builder().username(username).password(password).build();

        TypeDTO result = restTemplate.postForObject(uri, regularUserDTO, TypeDTO.class);

        if (result == null)
            throw new InvalidCredentialsException("Invalid credentials or already logged");

        Authentication auth = new UsernamePasswordAuthenticationToken(UserDTO.builder().userType(result).username(username).build(), null);
        SecurityContextHolder.getContext().setAuthentication(auth);

        notificationWebSocketHandler.sendMessage("START####" + username);

        return result;
    }

    @Override
    public void logOut() throws IOException {
        UserDTO principal = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        notificationWebSocketHandler.sendMessage("STOP####" + principal.getUsername());

        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Override
    public TypeDTO getUserType() {
        UserDTO principal = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUserType();
    }

}
