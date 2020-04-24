package com.vetshop.services.implementations;

import com.vetshop.dtos.ItemDTO;
import com.vetshop.entities.Item;
import com.vetshop.repositories.ItemRepository;
import com.vetshop.services.ItemService;
import com.vetshop.services.exceptions.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Item service.
 */
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    /**
     * Instantiates a new Item service.
     *
     * @param itemRepository the item repository
     */
    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemDTO createItem(String name, double quantity) {
        return new ItemDTO(itemRepository.save(Item.builder().name(name).neededIn(new ArrayList<>()).stock(quantity).build()));
    }

    @Override
    public ItemDTO updateItem(int id, String name, String quantity) throws NoSuchEntityException {
        Item existing = itemRepository.findById(id).orElse(null);
        Item item = null;
        if (existing != null) {
            item = Item.builder().stock(Integer.parseInt(quantity)).name(name).neededIn(existing.getNeededIn()).itemId(id).build();
            return new ItemDTO(itemRepository.save(item));
        } else {
            throw new NoSuchEntityException("The requested item doesn't exist");
        }
    }

    @Override
    public ItemDTO deleteItem(int id) throws NoSuchEntityException {
        Item item = itemRepository.findById(id).orElse(null);
        if (item != null) {
            itemRepository.delete(item);
        } else {
            throw new NoSuchEntityException("Consultation with given id doesn't exist");
        }
        return new ItemDTO(item);
    }

    @Override
    public List<String> getAllItemsNames() {
        return itemRepository.findAll().stream().map(Item::getName).collect(Collectors.toList());
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return itemRepository.findAll().stream().map(ItemDTO::new).collect(Collectors.toList());
    }
}
