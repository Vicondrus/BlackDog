package com.vetshop.services.implementations;

import com.vetshop.dtos.ItemDTO;
import com.vetshop.dtos.ItemListWrapperDTO;
import com.vetshop.dtos.StringsListWrapperDTO;
import com.vetshop.exceptions.FieldException;
import com.vetshop.services.ItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * The type Item service.
 */
@Service
public class ItemServiceImpl implements ItemService {

    private final String uriRoot;
    private RestTemplate restTemplate = new RestTemplate();

    /**
     * Instantiates a new Item service.
     *
     * @param hostAndPort the host and port
     */
    public ItemServiceImpl(@Value("${server.host-and-port}") String hostAndPort) {
        this.uriRoot = "http://" + hostAndPort;
    }

    /**
     * Sets rest template.
     *
     * @param restTemplate the rest template
     */
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<String> getAllItemsNames() {
        final String uri = uriRoot + "/getAllItemsNames";

        StringsListWrapperDTO strings = restTemplate.getForObject(uri, StringsListWrapperDTO.class);

        return strings.getStrings();
    }

    @Override
    public List<ItemDTO> getAllItems() {
        final String uri = uriRoot + "/getAllItems";

        ItemListWrapperDTO items = restTemplate.getForObject(uri, ItemListWrapperDTO.class);

        return items.getList();
    }

    @Override
    public ItemDTO postCreateItem(String name, String quantity) throws FieldException {
        final String uri = uriRoot + "/createItem";

        int quant;
        try {
            quant = Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            throw new FieldException("Quantity cannot be negative");
        }
        if (quant < 0)
            throw new FieldException("Quantity cannot be negative");

        ItemDTO toCreate = ItemDTO.builder().name(name).quantity(quant).build();

        return restTemplate.postForObject(uri, toCreate, ItemDTO.class);
    }

    @Override
    public ItemDTO deleteItem(ItemDTO item) {
        final String uri = uriRoot + "/deleteItem";

        item = restTemplate.postForObject(uri, item, ItemDTO.class);
        return item;
    }

    @Override
    public ItemDTO postUpdateItem(int itemId, String name, String quantity) throws FieldException {
        final String uri = uriRoot + "/updateItem";

        int quant;
        try {
            quant = Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            throw new FieldException("Quantity cannot be negative");
        }
        if (quant < 0)
            throw new FieldException("Quantity cannot be negative");

        ItemDTO toUpdate = ItemDTO.builder().itemId(itemId).name(name).quantity(quant).build();

        return restTemplate.postForObject(uri, toUpdate, ItemDTO.class);
    }

}
