package com.vetshop.repositories;

import com.vetshop.entities.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserRepository extends JpaRepository<AdminUser, Integer> {

    AdminUser findByUsername(String username);

}
