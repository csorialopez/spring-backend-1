package com.sales.market.service;

import com.sales.market.model.Buy;
import com.sales.market.model.ItemInventory;
import com.sales.market.model.Sale;

import java.util.Set;

public interface ItemInventoryService extends GenericService<ItemInventory> {
    void createOrUpdateItemInventoryAfterBuy(Buy buy);

    void updateItemInventoryAfterSale(Sale sale);
    void ifQuantityLessThanLowerBoundThreshold(Set<ItemInventory> lista);

    ItemInventory findByItemInventoryByItemid(Long idItem);
}