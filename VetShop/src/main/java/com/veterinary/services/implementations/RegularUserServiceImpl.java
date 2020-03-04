package com.veterinary.services.implementations;

import com.veterinary.entities.Animal;
import com.veterinary.entities.RegularUser;
import com.veterinary.repositories.RegularUserRepository;
import com.veterinary.services.RegularUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegularUserServiceImpl implements RegularUserService {

    @Autowired
    private RegularUserRepository regUserRepo;

    @Override
    public List<Animal> getAllAnimalsConsultedBy(RegularUser regularUser){
        RegularUser user = regUserRepo.findById(regularUser.getIdUser()).orElse(null);
        if(user == null)
            return null;
        return user.getConsultedAnimals();
    }

    @Override
    public RegularUser getByUsername(String username) {
        return regUserRepo.findByUsername(username);
    }

    @Override
    public List<RegularUser> getAll() {
        return  regUserRepo.findAll();
    }

    @Override
    public RegularUser save(RegularUser regularUser) {
        return regUserRepo.save(regularUser);
    }

    @Override
    public RegularUser update(RegularUser regularUser) {
        if(regularUser == null)
            return null;
        RegularUser found = regUserRepo.findById(regularUser.getIdUser()).orElse(null);
        if(found == null)
            return null;
        regularUser.setConsultations(found.getConsultations());
        regularUser.setIdUser(found.getIdUser());
        regularUser.setUserType(found.getUserType());
        return regUserRepo.save(regularUser);
    }

    @Override
    public RegularUser delete(RegularUser regularUser) {
        return null;
    }
}
