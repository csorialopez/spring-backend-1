package com.sales.market.service;

import com.sales.market.model.Contract;
import com.sales.market.repository.ContractRepository;
import com.sales.market.repository.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl extends GenericServiceImpl<Contract> implements ContractService {
    private final ContractRepository repository;

    public ContractServiceImpl(ContractRepository repository) {
        this.repository = repository;
    }

    @Override
    protected GenericRepository<Contract> getRepository() {
        return repository;
    }


}