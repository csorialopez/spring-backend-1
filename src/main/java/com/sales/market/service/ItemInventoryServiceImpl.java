package com.sales.market.service;

import com.sales.market.model.*;
import com.sales.market.repository.ItemInventoryRepository;
import com.sales.market.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ItemInventoryServiceImpl extends GenericServiceImpl<ItemInventory> implements ItemInventoryService {
    private final ItemInventoryRepository repository;
    private final ItemInventoryEntryService itemInventoryEntryService;
    private final EmployeeService employeeService;

    @Value("${item.inventory.lowerBoundThreshold}")
    private int lowerBoundThreshold;
    @Value("${item.inventory.upperBoundThreshold}")
    private int upperBoundThreshold;

    public ItemInventoryServiceImpl(ItemInventoryRepository repository, ItemInventoryEntryService itemInventoryEntryService, EmployeeService employeeService) {
        this.repository = repository;
        this.itemInventoryEntryService = itemInventoryEntryService;
        this.employeeService = employeeService;
    }

    @Override
    protected GenericRepository<ItemInventory> getRepository() {
        return repository;
    }

    @Override
    public void createOrUpdateItemInventoryAfterBuy(Buy buy) {
        ItemInventory itemInventorySaved;
        if (repository.findItemInventoryByItem_Id(buy.getItem().getId()) != null) {
            ItemInventory itemInventory = repository.findItemInventoryByItem_Id(buy.getItem().getId());
            itemInventory.setStockQuantity(itemInventory.getStockQuantity() + buy.getQuantity());
            itemInventory.setTotalPrice(itemInventory.getTotalPrice().add(buy.getTotalCost()));
            itemInventorySaved = save(itemInventory);
        } else {
            ItemInventory itemInventory = new ItemInventory();
            itemInventory.setItem(buy.getItem());
            itemInventory.setUpperBoundThreshold(upperBoundThreshold);
            itemInventory.setLowerBoundThreshold(lowerBoundThreshold);
            itemInventory.setStockQuantity(buy.getQuantity());
            itemInventory.setTotalPrice(buy.getTotalCost());
            itemInventorySaved = save(itemInventory);
        }
        itemInventoryEntryService.createEntryItemInventory(itemInventorySaved, buy.getQuantity(), MovementType.BUY);
    }

    @Override
    public void updateItemInventoryAfterSale(Sale sale){
        List<ItemInventory> inventoryList = new ArrayList<>();
        Set<ItemInventory> itemInventoriesSet = new HashSet<>();
        sale.getItemInstanceSet().forEach(itemInstance -> {
            ItemInventory itemInventory = repository.findItemInventoryByItem_Id(itemInstance.getItem().getId());
            itemInventory.setStockQuantity(itemInventory.getStockQuantity() - 1);
            itemInventory.setTotalPrice(itemInventory.getTotalPrice().subtract(itemInstance.getPurchaseBuy()));
            itemInventory.setIncome(itemInstance.getSellingPrice().subtract(itemInstance.getPurchaseBuy()));
            ItemInventory itemInventorySaved = save(itemInventory);
            inventoryList.add(itemInventorySaved);
        });
        itemInventoryEntryService.createSaleEntryItemInventory(inventoryList);
        itemInventoriesSet.addAll(inventoryList);
        ifQuantityLessThanLowerBoundThreshold(itemInventoriesSet);
    }

    @Override
    public void ifQuantityLessThanLowerBoundThreshold(Set<ItemInventory> lista) {
        lista.forEach(itemInventory -> {
            if (itemInventory.getStockQuantity() <= itemInventory.getLowerBoundThreshold()){
                employeeService.sendNotificationOfInventoryStock(itemInventory);
            }
        });
    }

    @Override
    public ItemInventory findByItemInventoryByItemid(Long idItem) {
        return repository.findItemInventoryByItem_Id(idItem);
    }
}