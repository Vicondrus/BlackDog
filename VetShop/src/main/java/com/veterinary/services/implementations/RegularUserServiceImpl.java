package com.veterinary.services.implementations;

import com.veterinary.dtos.AnimalDTO;
import com.veterinary.dtos.RegularUserDTO;
import com.veterinary.entities.Animal;
import com.veterinary.entities.RegularUser;
import com.veterinary.repositories.RegularUserRepository;
import com.veterinary.services.RegularUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegularUserServiceImpl implements RegularUserService {

    @Autowired
    private RegularUserRepository regUserRepo;

    @Override
    public RegularUserDTO getByUsername(String username) {
        return new RegularUserDTO(regUserRepo.findByUsername(username));
    }

    @Override
    public List<RegularUserDTO> getAll() {
        return  regUserRepo.findAll().stream().map(RegularUserDTO::new).collect(Collectors.toList());
    }

    @Override
    public RegularUserDTO save(String username, String password, String fullName, String userType) {
        RegularUser regularUser = RegularUser.builder().consultations(new ArrayList<>()).build();
        regularUser.setFullName(fullName);
        regularUser.setPassword(password);
        regularUser.setUsername(username);
        return new RegularUserDTO(regUserRepo.save(regularUser));
    }

    @Override
    public RegularUserDTO update(int id, String username, String password, String fullName) {
        RegularUser regularUser = regUserRepo.findById(id).orElse(null);
        regularUser.setUsername(username);
        regularUser.setPassword(password);
        regularUser.setFullName(fullName);
        regUserRepo.save(regularUser);
        return new RegularUserDTO(regularUser);
    }

    @Override
    public RegularUserDTO delete(int id) {
        RegularUser regularUser = regUserRepo.findById(id).orElse(null);
        regUserRepo.delete(regularUser);
        return new RegularUserDTO(regularUser);
    }
}
