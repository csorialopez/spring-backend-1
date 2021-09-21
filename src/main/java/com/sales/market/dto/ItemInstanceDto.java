package com.sales.market.dto;

import com.sales.market.model.ItemInstance;
import com.sales.market.model.ItemInstanceStatus;

import java.math.BigDecimal;

public class ItemInstanceDto extends DtoBase<ItemInstance> {
    private ItemDto item;
    private String identifier;// sku
    private Boolean featured = Boolean.FALSE;
    private BigDecimal purchaseBuy;
    private BigDecimal sellingPrice;
    private ItemInstanceStatus itemInstanceStatus;

    public ItemInstanceDto() {}
    public ItemInstanceDto(ItemDto item, String identifier, Boolean featured, BigDecimal purchaseBuy, BigDecimal sellingPrice) {
        this.item = item;
        this.identifier = identifier;
        this.featured = featured;
        this.purchaseBuy = purchaseBuy;
        this.sellingPrice = sellingPrice;
    }

    public ItemDto getItem() {
        return item;
    }

    public void setItem(ItemDto item) {
        this.item = item;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public BigDecimal getPurchaseBuy() {
        return purchaseBuy;
    }

    public void setPurchaseBuy(BigDecimal purchaseBuy) {
        this.purchaseBuy = purchaseBuy;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public ItemInstanceStatus getItemInstanceStatus() {
        return itemInstanceStatus;
    }

    public void setItemInstanceStatus(ItemInstanceStatus itemInstanceStatus) {
        this.itemInstanceStatus = itemInstanceStatus;
    }
}
