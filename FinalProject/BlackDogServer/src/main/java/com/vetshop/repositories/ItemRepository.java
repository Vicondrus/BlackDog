package com.vetshop.repositories;

import com.vetshop.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Item repository.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    /**
     * Find by name item.
     *
     * @param name the name
     * @return the item
     */
    Item findByName(String name);

}
