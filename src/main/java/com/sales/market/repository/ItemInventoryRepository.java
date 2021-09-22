package com.sales.market.repository;

import com.sales.market.model.ItemInventory;

public interface ItemInventoryRepository extends GenericRepository<ItemInventory> {

    ItemInventory findItemInventoryByItem_Id(Long item_id);
}