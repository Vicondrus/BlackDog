package com.veterinary.services.implementations;

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
    public List<RegularUser> getAll() {
        return  regUserRepo.findAll();
    }

    @Override
    public RegularUser save(RegularUser regularUser) {
        return regUserRepo.save(regularUser);
    }

    @Override
    public RegularUser update(RegularUser regularUser) {
        return null;
    }

    @Override
    public RegularUser delete(RegularUser regularUser) {
        return null;
    }
}
