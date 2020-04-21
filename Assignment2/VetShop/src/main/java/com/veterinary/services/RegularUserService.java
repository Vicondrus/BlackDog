package com.veterinary.services;

import com.veterinary.dtos.AnimalDTO;
import com.veterinary.dtos.RegularUserDTO;
import com.veterinary.entities.Animal;
import com.veterinary.entities.RegularUser;
import com.veterinary.entities.UserType;
import com.veterinary.services.exceptions.AlreadyExistingException;
import com.veterinary.services.exceptions.FieldException;
import com.veterinary.services.exceptions.NoSuchEntityException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RegularUserService {

    List<RegularUserDTO> getAll();

    RegularUserDTO save(String username, String password, String fullName, String type) throws AlreadyExistingException, NoSuchEntityException, FieldException;

    RegularUserDTO update(int id, String username, String password, String fullName) throws NoSuchEntityException, FieldException;

    RegularUserDTO delete(int id) throws NoSuchEntityException;

    RegularUserDTO getByUsername(String username) throws NoSuchEntityException;

}
