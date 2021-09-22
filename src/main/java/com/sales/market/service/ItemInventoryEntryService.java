package com.sales.market.service;

import com.sales.market.model.ItemInventory;
import com.sales.market.model.ItemInventoryEntry;
import com.sales.market.model.MovementType;

import java.util.List;

public interface ItemInventoryEntryService extends GenericService<ItemInventoryEntry> {
    void createEntryItemInventory(ItemInventory itemInventory, int quantity, MovementType movementType);
    void createSaleEntryItemInventory(List<ItemInventory> itemsInventory);
}