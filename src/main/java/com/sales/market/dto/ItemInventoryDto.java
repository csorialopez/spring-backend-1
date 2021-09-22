package com.sales.market.dto;

import com.sales.market.model.ItemInventory;

import java.math.BigDecimal;

public class ItemInventoryDto extends DtoBase<ItemInventory> {
    private ItemDto item;
    private int stockQuantity;
    private int lowerBoundThreshold;
    private int upperBoundThreshold;
    private BigDecimal totalPrice;
    private BigDecimal income;

    public ItemDto getItem() {
        return item;
    }

    public void setItem(ItemDto item) {
        this.item = item;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getLowerBoundThreshold() {
        return lowerBoundThreshold;
    }

    public void setLowerBoundThreshold(int lowerBoundThreshold) {
        this.lowerBoundThreshold = lowerBoundThreshold;
    }

    public int getUpperBoundThreshold() {
        return upperBoundThreshold;
    }

    public void setUpperBoundThreshold(int upperBoundThreshold) {
        this.upperBoundThreshold = upperBoundThreshold;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }
}