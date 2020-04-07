package com.vetshop.repositories;

import com.vetshop.entities.RegularUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegularUserRepository extends JpaRepository<RegularUser, Integer> {

    RegularUser findByUsername(String username);

}
