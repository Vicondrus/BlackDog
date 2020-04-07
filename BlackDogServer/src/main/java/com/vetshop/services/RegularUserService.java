package com.vetshop.services;

import com.vetshop.dtos.RegularUserDTO;
import com.vetshop.services.exceptions.AlreadyExistingException;
import com.vetshop.services.exceptions.NoSuchEntityException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RegularUserService {

    List<RegularUserDTO> getAll();

    RegularUserDTO save(String username, String password, String fullName, String type) throws AlreadyExistingException, NoSuchEntityException;

    RegularUserDTO update(int id, String username, String password, String fullName) throws NoSuchEntityException;

    RegularUserDTO delete(int id) throws NoSuchEntityException;

    RegularUserDTO getByUsername(String username) throws NoSuchEntityException;

}
