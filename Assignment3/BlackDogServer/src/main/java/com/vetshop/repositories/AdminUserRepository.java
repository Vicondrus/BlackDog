package com.vetshop.repositories;

import com.vetshop.entities.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Admin user repository.
 */
public interface AdminUserRepository extends JpaRepository<AdminUser, Integer> {

    /**
     * Find by username admin user.
     *
     * @param username the username
     * @return the admin user
     */
    AdminUser findByUsername(String username);

}
