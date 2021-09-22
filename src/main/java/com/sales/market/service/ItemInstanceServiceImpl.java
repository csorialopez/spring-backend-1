/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service;

import com.sales.market.model.Buy;
import com.sales.market.model.ItemInstance;
import com.sales.market.model.ItemInstanceStatus;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.ItemInstanceRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ItemInstanceServiceImpl extends GenericServiceImpl<ItemInstance> implements ItemInstanceService {
    private final ItemInstanceRepository repository;
    private final ItemService itemService;

    public ItemInstanceServiceImpl(ItemInstanceRepository repository, ItemService itemService) {
        this.repository = repository;
        this.itemService = itemService;
    }

    @Override
    protected GenericRepository<ItemInstance> getRepository() {
        return repository;
    }

    @Override
    public ItemInstance bunchSave(ItemInstance itemInstance) {
        if (itemInstance.getItem() != null) {
            itemService.save(itemInstance.getItem());
        }
        return super.bunchSave(itemInstance);
    }

    @Override
    public void saveItemInstances(Buy buySaved) {
        List<ItemInstance> listaInstances = new ArrayList<>();
        BigDecimal priceUnitario = buySaved.getTotalCost().divide(new BigDecimal(buySaved.getQuantity()), 2, RoundingMode.CEILING); //Redondea a infinito positivo
        BigDecimal priceSale = priceUnitario.add(priceUnitario.multiply(new BigDecimal(0.13)));
        for (int i = 0; i < buySaved.getQuantity() ; i++) {
            String generatedString = RandomStringUtils.randomAlphanumeric(12);
            ItemInstance newInstance = new ItemInstance((buySaved.getItem()), "SKU-" + generatedString , priceUnitario, priceSale , ItemInstanceStatus.AVAILABLE);
            listaInstances.add(newInstance);
        }
        saveAll(listaInstances);
    }

    @Override
    public ItemInstance getItemInstanceByIdentifier(String identifier) {
        return repository.findByIdentifier(identifier);
    }

    @Override
    public Set<ItemInstance> getItemInstancesListByIdItem(Long idItem) {
        List<ItemInstance> items = repository.findAllByItem_Id(idItem);
        Set<ItemInstance> list = new HashSet<>();
        items.forEach(itemInstance -> {
            list.add(itemInstance);
        });
        return list;
    }
}
