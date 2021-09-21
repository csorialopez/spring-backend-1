/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.dto;

import com.sales.market.model.ItemInstance;
import com.sales.market.model.Sale;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SaleDto extends DtoBase<Sale> {
    private Long employeeId;
    private Date date;
    private String description;
    private Long clientId;
    private Set<ItemInstanceDto> itemInstanceSet = new HashSet<>();
    private int quantityProduct;
    private BigDecimal totalAmount;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Set<ItemInstanceDto> getItemInstanceSet() {
        return itemInstanceSet;
    }

    public void setItemInstanceSet(Set<ItemInstanceDto> itemInstanceSet) {
        this.itemInstanceSet = itemInstanceSet;
    }

    public int getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(int quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
