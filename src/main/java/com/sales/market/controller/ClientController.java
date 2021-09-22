package com.sales.market.controller;

import com.sales.market.dto.ClientDto;
import com.sales.market.model.Client;
import com.sales.market.service.ClientService;
import com.sales.market.service.GenericService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController extends GenericController<Client, ClientDto> {
    private ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @Override
    protected GenericService getService() {
        return service;
    }
}
