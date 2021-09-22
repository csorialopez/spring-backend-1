package com.sales.market.controller;

import com.sales.market.dto.ContractDto;
import com.sales.market.model.Contract;
import com.sales.market.service.ContractService;
import com.sales.market.service.GenericService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController extends GenericController<Contract, ContractDto> {
    private ContractService service;

    public ContractController(ContractService service) {
        this.service = service;
    }

    @Override
    protected GenericService getService() {
        return service;
    }
}
