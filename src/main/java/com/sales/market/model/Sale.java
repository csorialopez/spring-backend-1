package com.sales.market.model;

import com.sales.market.dto.SaleDto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Sale extends ModelBase<SaleDto> {
    @OneToOne(optional = false)
    private Employee employee;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;

    @OneToMany(mappedBy = "sale", fetch = FetchType.EAGER)
    private Set<ItemInstance> itemInstanceSet = new HashSet<>();

    private int quantityProduct;
    private BigDecimal totalAmount;
    private String description;

    @OneToOne
    private Client client;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public Set<ItemInstance> getItemInstanceSet() {
        return itemInstanceSet;
    }

    public void setItemInstanceSet(Set<ItemInstance> itemInstanceSet) {
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
