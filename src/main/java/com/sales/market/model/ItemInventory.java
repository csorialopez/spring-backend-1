package com.sales.market.model;

import com.sales.market.dto.ItemInventoryDto;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class ItemInventory extends ModelBase<ItemInventoryDto> {

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    private int stockQuantity;
    private int lowerBoundThreshold;
    private int upperBoundThreshold;
    private BigDecimal totalPrice;
    private BigDecimal income;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
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
