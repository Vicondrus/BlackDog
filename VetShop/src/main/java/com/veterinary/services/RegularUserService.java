package com.veterinary.services;

import com.veterinary.dtos.AnimalDTO;
import com.veterinary.dtos.RegularUserDTO;
import com.veterinary.entities.Animal;
import com.veterinary.entities.RegularUser;
import com.veterinary.entities.UserType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RegularUserService {

    List<RegularUserDTO> getAll();

    RegularUserDTO save(String username, String password, String fullName, String type);

    RegularUserDTO update(int id, String username, String password, String fullName);

    RegularUserDTO delete(int id);

    RegularUserDTO getByUsername(String username);

}
