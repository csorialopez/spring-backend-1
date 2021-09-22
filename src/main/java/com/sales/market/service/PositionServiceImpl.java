package com.sales.market.service;

import com.sales.market.model.Position;
import com.sales.market.repository.PositionRepository;
import com.sales.market.repository.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class PositionServiceImpl extends GenericServiceImpl<Position> implements PositionService {
    private final PositionRepository repository;

    public PositionServiceImpl(PositionRepository repository) {
        this.repository = repository;
    }

    @Override
    protected GenericRepository<Position> getRepository() {
        return repository;
    }

    @Override
    public Position findByName(String name) {
        return repository.findByName(name);
    }
}