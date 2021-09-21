package com.sales.market.model;

import com.sales.market.dto.ItemInstanceDto;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "identifier")})
public class ItemInstance extends ModelBase<ItemInstanceDto> {

    @OneToOne
    private Item item;
    private String identifier;// sku

    private Boolean featured = Boolean.FALSE;

    private BigDecimal purchaseBuy;
    private BigDecimal sellingPrice;

    private ItemInstanceStatus itemInstanceStatus;

    @ManyToOne
    public Sale sale;

    public ItemInstance(){}

    public ItemInstance(Item item, String identifier,  BigDecimal purchaseBuy, BigDecimal sellingPrice, ItemInstanceStatus itemInstanceStatus) {
        this.item = item;
        this.identifier = identifier;
        this.purchaseBuy = purchaseBuy;
        this.sellingPrice = sellingPrice;
        this.itemInstanceStatus = itemInstanceStatus;
    }

    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }

    public String getIdentifier() {
        return identifier;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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

    public Boolean getFeatured() {
        return featured;
    }
    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public ItemInstanceStatus getItemInstanceStatus() {
        return itemInstanceStatus;
    }

    public void setItemInstanceStatus(ItemInstanceStatus itemInstanceStatus) {
        this.itemInstanceStatus = itemInstanceStatus;
    }
}
