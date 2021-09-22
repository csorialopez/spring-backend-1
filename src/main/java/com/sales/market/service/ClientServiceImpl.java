package com.sales.market.service;

import com.sales.market.model.Client;
import com.sales.market.repository.ClientRepository;
import com.sales.market.repository.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends GenericServiceImpl<Client> implements ClientService {
    private final ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    protected GenericRepository<Client> getRepository() {
        return repository;
    }
}