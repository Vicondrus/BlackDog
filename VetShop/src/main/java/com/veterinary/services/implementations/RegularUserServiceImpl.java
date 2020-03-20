package com.veterinary.services.implementations;

import com.veterinary.dtos.AnimalDTO;
import com.veterinary.dtos.RegularUserDTO;
import com.veterinary.entities.Animal;
import com.veterinary.entities.RegularUser;
import com.veterinary.repositories.RegularUserRepository;
import com.veterinary.services.RegularUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public RegularUserDTO save(RegularUser regularUser) {
        return new RegularUserDTO(regUserRepo.save(regularUser));
    }

    @Override
    public RegularUserDTO update(RegularUser regularUser) {
        if(regularUser == null) {
            return null;
        }
        RegularUser found = regUserRepo.findById(regularUser.getIdUser()).orElse(null);
        if(found == null) {
            return null;
        }
        regularUser.setConsultations(found.getConsultations());
        regularUser.setIdUser(found.getIdUser());
        regularUser.setUserType(found.getUserType());
        return new RegularUserDTO(regUserRepo.save(regularUser));
    }

    @Override
    public RegularUserDTO delete(RegularUser regularUser) {
        return null;
    }
}
