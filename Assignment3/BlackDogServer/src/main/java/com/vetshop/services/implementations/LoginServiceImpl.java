package com.vetshop.services.implementations;

import com.vetshop.dtos.TypeDTO;
import com.vetshop.entities.User;
import com.vetshop.repositories.UserRepository;
import com.vetshop.services.LoginService;
import com.vetshop.services.exceptions.AlreadyExistingException;
import com.vetshop.services.exceptions.InvalidCredentialsException;
import com.vetshop.util.ActiveUsersStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Login service.
 */
@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    private final ActiveUsersStore activeUsersStore;

    /**
     * Instantiates a new Login service.
     *
     * @param userRepository the user repository
     */
    @Autowired
    public LoginServiceImpl(UserRepository userRepository, ActiveUsersStore activeUsersStore){
        this.userRepository = userRepository;
        this.activeUsersStore = activeUsersStore;
    }

    @Override
    public TypeDTO loginUser(String username, String password) throws InvalidCredentialsException, AlreadyExistingException {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new InvalidCredentialsException("No such user");
        else if(user.getPassword().equals(password)){
            if (!activeUsersStore.checkUser(user)) {
                activeUsersStore.addUser(user);
                return TypeDTO.valueOf(user.getUserTypeAsString());
            }else{
                throw new AlreadyExistingException("The user is already logged");
            }        }else{
            throw new InvalidCredentialsException("Wrong Password");
        }
    }
}
