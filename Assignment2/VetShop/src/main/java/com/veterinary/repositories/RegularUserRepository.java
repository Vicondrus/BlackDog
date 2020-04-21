package com.veterinary.repositories;

import com.veterinary.entities.RegularUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegularUserRepository extends JpaRepository<RegularUser, Integer> {

    RegularUser findByUsername(String username);

}
