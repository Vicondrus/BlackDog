package com.veterinary.services;

import com.veterinary.dtos.AnimalDTO;
import com.veterinary.dtos.RegularUserDTO;
import com.veterinary.entities.Animal;
import com.veterinary.entities.RegularUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RegularUserService {

    List<RegularUserDTO> getAll();

    RegularUserDTO save(RegularUser regularUser);

    RegularUserDTO update(RegularUser regularUser);

    RegularUserDTO delete(RegularUser regularUser);

    RegularUserDTO getByUsername(String username);

}
