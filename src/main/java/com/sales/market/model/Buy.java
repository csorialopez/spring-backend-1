/**
 * @author: Edson A. Terceros T.
 * 17
 */

package com.sales.market.model;

import com.sales.market.dto.BuyDto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Buy extends ModelBase<BuyDto> {

    private int quantity;
    private BigDecimal totalCost;

    @ManyToOne
    private Item item;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
