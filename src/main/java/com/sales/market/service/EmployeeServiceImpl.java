package com.sales.market.service;

import com.sales.market.model.Employee;
import com.sales.market.model.ItemInventory;
import com.sales.market.model.Position;
import com.sales.market.model.PositionEnum;
import com.sales.market.repository.EmployeeRepository;
import com.sales.market.repository.GenericRepository;
import com.sales.market.service.mail.EmailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl extends GenericServiceImpl<Employee> implements EmployeeService {
    private final EmployeeRepository repository;
    private final EmailService emailService;
    private final PositionService positionService;

    public EmployeeServiceImpl(EmployeeRepository repository, EmailService emailService, PositionService positionService) {
        this.repository = repository;
        this.emailService = emailService;
        this.positionService = positionService;
    }

    @Override
    protected GenericRepository<Employee> getRepository() {
        return repository;
    }

    @Override
    public List<Employee> findEmployeeActiveByPositionId(Long idPosition) {
        return repository.findEmployeeActiveByPositionId(idPosition);
    }

    @Override
    public void sendNotificationOfInventoryStock(ItemInventory itemInventory) {
        Position positionG = positionService.findByName(PositionEnum.GERENTE.getPosition());
        List<Employee> listG = findEmployeeActiveByPositionId(positionG.getId());
        listG.forEach(gerente -> {
            emailService.sendMessage(gerente.getEmail(),
                    "Notificacion de inventario",
                    "Comunicarle que al item "+ itemInventory.getItem().getName()+" en inventario le queda cantidad en stock de " + itemInventory.getStockQuantity() +
                    ", se sugiere realizar el pedido correspondiente.");
        });
    }
}