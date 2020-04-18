package com.vetshop.repositories;

import com.vetshop.entities.RegularUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Regular user repository.
 */
@Repository
public interface RegularUserRepository extends JpaRepository<RegularUser, Integer> {

    /**
     * Find by username regular user.
     *
     * @param username the username
     * @return the regular user
     */
    RegularUser findByUsername(String username);

}
