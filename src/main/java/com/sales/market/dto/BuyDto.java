/**
 * @author: Edson A. Terceros T.
 * 17
 */

package com.sales.market.dto;

import com.sales.market.model.Buy;
import com.sales.market.model.Item;
import org.modelmapper.ModelMapper;

import javax.persistence.ManyToOne;
import java.math.BigDecimal;

public class BuyDto extends DtoBase<Buy> {

    private ItemDto item;
    private int quantity;
    private BigDecimal totalCost;

    public ItemDto getItem() {
        return item;
    }

    public void setItem(ItemDto item) {
        this.item = item;
    }

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

    @Override
    public BuyDto toDto(Buy buy, ModelMapper mapper) {
        BuyDto dtoBase = super.toDto(buy, mapper);
        return dtoBase;
    }
}
