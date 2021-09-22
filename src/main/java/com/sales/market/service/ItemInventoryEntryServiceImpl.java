package com.sales.market.service;

import com.sales.market.model.ItemInventory;
import com.sales.market.model.ItemInventoryEntry;
import com.sales.market.model.MovementType;
import com.sales.market.repository.ItemInventoryEntryRepository;
import com.sales.market.repository.GenericRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemInventoryEntryServiceImpl extends GenericServiceImpl<ItemInventoryEntry> implements ItemInventoryEntryService {
    private final ItemInventoryEntryRepository repository;

    public ItemInventoryEntryServiceImpl(ItemInventoryEntryRepository repository) {
        this.repository = repository;
    }

    @Override
    protected GenericRepository<ItemInventoryEntry> getRepository() {
        return repository;
    }

    @Override
    public void createEntryItemInventory(ItemInventory itemInventory, int quantity, MovementType movementType) {
        ItemInventoryEntry itemInventoryEntry = new ItemInventoryEntry();
        itemInventoryEntry.setItemInventory(itemInventory);
        itemInventoryEntry.setMovementType(movementType);
        itemInventoryEntry.setQuantity(quantity);
        save(itemInventoryEntry);
    }

    @Override
    public void createSaleEntryItemInventory(List<ItemInventory> itemsInventory) {
        itemsInventory.forEach(itemInventory -> {
           createEntryItemInventory(itemInventory, 1, MovementType.SALE);
        });
    }
}