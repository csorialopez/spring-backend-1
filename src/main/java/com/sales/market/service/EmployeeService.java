package com.sales.market.service;

import com.sales.market.model.Employee;
import com.sales.market.model.ItemInventory;

import java.util.List;

public interface EmployeeService extends GenericService<Employee> {

    List<Employee> findEmployeeActiveByPositionId(Long idPosition);
    void sendNotificationOfInventoryStock(ItemInventory itemInventories);
}