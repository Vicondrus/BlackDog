package com.veterinary.repositories;

import com.veterinary.entities.RegularUser;
import com.veterinary.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);

}
