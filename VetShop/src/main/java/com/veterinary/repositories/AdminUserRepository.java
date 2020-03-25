package com.veterinary.repositories;

import com.veterinary.entities.AdminUser;
import com.veterinary.entities.RegularUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserRepository extends JpaRepository<AdminUser, Integer> {

    AdminUser findByUsername(String username);

}
