package com.sales.market.service;

import com.sales.market.model.Position;

public interface PositionService extends GenericService<Position> {
    Position findByName(String name);
}