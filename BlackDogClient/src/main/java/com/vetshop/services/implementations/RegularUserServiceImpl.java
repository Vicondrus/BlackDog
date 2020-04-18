package com.vetshop.services.implementations;

import com.vetshop.dtos.TypeDTO;
import com.vetshop.dtos.UserDTO;
import com.vetshop.dtos.UsersListWrapperDTO;
import com.vetshop.services.RegularUserService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * The type Regular user service.
 */
@Service
public class RegularUserServiceImpl implements RegularUserService {

    @Override
    public List<UserDTO> findAllRegularUsers(){
        final String uri = "http://localhost:8080/getAllRegularUsers";

        RestTemplate restTemplate = new RestTemplate();

        UsersListWrapperDTO consultations = restTemplate.getForObject(uri, UsersListWrapperDTO.class);

        if (consultations != null) {
            return consultations.getList();
        }else{
            return null;
        }
    }

    @Override
    public UserDTO deleteUser(UserDTO userDTO){
        final String uri = "http://localhost:8080/deleteUser";

        RestTemplate restTemplate = new RestTemplate();
        userDTO = restTemplate.postForObject(uri, userDTO, UserDTO.class);
        return userDTO;
    }

    @Override
    public UserDTO postCreateUser(String username, String password, String fullName){
        final String uri = "http://localhost:8080/createUser";

        RestTemplate restTemplate = new RestTemplate();

        UserDTO user = UserDTO.builder().username(username).userType(TypeDTO.REGULAR).password(password).fullName(fullName).build();

        user = restTemplate.postForObject(uri, user, UserDTO.class);

        return user;
    }

    @Override
    public UserDTO postUpdateUser(int id, String username, String password, String fullName){
        final String uri = "http://localhost:8080/updateUser";

        RestTemplate restTemplate = new RestTemplate();

        UserDTO user = UserDTO.builder().username(username).idUser(id).userType(TypeDTO.REGULAR).password(password).fullName(fullName).build();

        user = restTemplate.postForObject(uri, user, UserDTO.class);

        return user;
    }

}
