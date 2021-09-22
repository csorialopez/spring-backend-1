package com.sales.market.service;

import com.sales.market.exception.GenericException;
import com.sales.market.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BuyServiceTest {
    @Autowired
    private BuyService buyService;
    @Autowired
    private ItemInventoryService itemInventoryService;
    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private CategoryService categoryService;

    @Test
    public void testWhenRegisterBuyOfNewItemAndRegisterItemInventory(){
        Item item = createItem();

        Buy compraDeItem = new Buy();
        compraDeItem.setItem(item);
        compraDeItem.setQuantity(15);
        compraDeItem.setTotalCost(new BigDecimal(100));

        Buy savedBuy = buyService.save(compraDeItem);
        assertEquals(savedBuy.getQuantity(), 15);
        ItemInventory itemInventory = itemInventoryService.findByItemInventoryByItemid(savedBuy.getItem().getId());
        assertEquals(itemInventory.getStockQuantity() , 15);
    }


    @Test
    public void testWhenRegisterBuyOfItemExistsAndItemInventoryIncrementQuantity(){
        Item item = createItem();

        Buy compraDeItem = new Buy();
        compraDeItem.setItem(item);
        compraDeItem.setQuantity(15);
        compraDeItem.setTotalCost(new BigDecimal(100));
        Buy buy1 = buyService.save(compraDeItem);

        Buy compra2 = new Buy();
        compra2.setItem(buy1.getItem());
        compra2.setQuantity(5);
        compra2.setTotalCost(new BigDecimal(30));
        Buy buy2 = buyService.save(compra2);

        assertEquals(buy2.getQuantity(), 5);
        ItemInventory itemInventory = itemInventoryService.findByItemInventoryByItemid(buy1.getItem().getId());
        assertEquals(itemInventory.getStockQuantity() , 20);
    }

    @Test
    public void testWhenRegisterBuyWithOutItemAndShowExceptionMessage () throws  GenericException {
        assertThrows(GenericException.class, () -> {
            Buy compraDeItem = new Buy();
            compraDeItem.setQuantity(15);
            compraDeItem.setTotalCost(new BigDecimal(100));
            Buy buySaved = buyService.save(compraDeItem);

            assertEquals(buySaved.getQuantity(), 15);
            ItemInventory itemInventory = itemInventoryService.findByItemInventoryByItemid(buySaved.getItem().getId());
            assertEquals(itemInventory.getStockQuantity() , 15);
        });
    }

    public Item createItem() {
        Category category = new Category();
        category.setName("LIMPIEZA");
        category.setCode("LIM");
        categoryService.save(category);

        SubCategory subCategory = new SubCategory();
        subCategory.setName("DETERGENTES");
        subCategory.setCode("DET-CODE");
        subCategory.setCategory(category);
        SubCategory detSubcategori = subCategoryService.save(subCategory);

        Item item = new Item();
        item.setName("ACE PATITO");
        item.setCode("DET-PATITO");
        item.setSubCategory(detSubcategori);

        return item;
    }
}