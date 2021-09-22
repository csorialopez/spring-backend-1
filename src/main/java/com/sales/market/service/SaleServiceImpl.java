package com.sales.market.service;

import com.sales.market.exception.GenericException;
import com.sales.market.model.ItemInstance;
import com.sales.market.model.ItemInstanceStatus;
import com.sales.market.model.Sale;
import com.sales.market.repository.SaleRepository;
import com.sales.market.repository.GenericRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Service
public class SaleServiceImpl extends GenericServiceImpl<Sale> implements SaleService {
    private final SaleRepository repository;
    private final ItemInstanceService itemInstanceService;
    private final ItemInventoryService itemInventoryService;

    public SaleServiceImpl(SaleRepository repository, ItemInstanceService itemInstanceService, ItemInventoryService itemInventoryService) {
        this.repository = repository;
        this.itemInstanceService = itemInstanceService;
        this.itemInventoryService = itemInventoryService;
    }

    @Override
    protected GenericRepository<Sale> getRepository() {
        return repository;
    }

    @Override
    public Sale save(Sale model) {
        try {
            Set<ItemInstance> itemInstanceSet = new HashSet<ItemInstance>();
            model.getItemInstanceSet().forEach(itemInstance -> {
                ItemInstance item = itemInstanceService.getItemInstanceByIdentifier(itemInstance.getIdentifier());
                item.setItemInstanceStatus(ItemInstanceStatus.SOLD);
                itemInstanceSet.add(item);
            });
            model.setItemInstanceSet(itemInstanceSet);
            Sale saleSaved = super.save(model);
            itemInventoryService.updateItemInventoryAfterSale(saleSaved);
            return saleSaved;
        } catch (Exception e) {
            throw new GenericException("Error saving the sale", HttpStatus.BAD_REQUEST);
        }
    }
}