package com.veterinary.services;

import com.veterinary.entities.Animal;
import com.veterinary.entities.RegularUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RegularUserService {

    List<RegularUser> getAll();

    RegularUser save(RegularUser regularUser);

    RegularUser update(RegularUser regularUser);

    RegularUser delete(RegularUser regularUser);

    List<Animal> getAllAnimalsConsultedBy(RegularUser regularUser);

    RegularUser getByUsername(String username);

}
