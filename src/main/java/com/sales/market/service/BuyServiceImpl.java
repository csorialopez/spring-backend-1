package com.sales.market.service;

import com.sales.market.exception.GenericException;
import com.sales.market.model.*;
import com.sales.market.repository.BuyRepository;
import com.sales.market.repository.GenericRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BuyServiceImpl extends GenericServiceImpl<Buy> implements BuyService {
    private final BuyRepository repository;
    private final ItemService itemService;
    private final ItemInstanceService itemInstanceService;
    private final ItemInventoryService itemInventoryService;


    public BuyServiceImpl(BuyRepository repository, ItemService itemService, ItemInstanceService itemInstanceService, ItemInventoryService itemInventoryService) {
        this.repository = repository;
        this.itemService = itemService;
        this.itemInstanceService = itemInstanceService;
        this.itemInventoryService = itemInventoryService;
    }

    @Override
    protected GenericRepository<Buy> getRepository() {
        return repository;
    }

    @Override
    public Buy save(Buy model) {
        try {
            Item item;
            if ( model.getItem().getId() == null){
               item =  itemService.save(model.getItem());
            } else {
                item = itemService.findById(model.getItem().getId());
            }
            model.setItem(item);
            Buy buySaved = super.save(model);
            itemInstanceService.saveItemInstances(buySaved);
            itemInventoryService.createOrUpdateItemInventoryAfterBuy(buySaved);
            return buySaved;
        } catch (Exception e) {
            throw new GenericException("Error saving the purchase", HttpStatus.BAD_REQUEST);
        }
    }
}
