package com.sales.market.model;

public enum PositionEnum {
    GERENTE("Gerente"),
    CAJERO("Cajero"),
    ACOMODADOR("Acomodador"),
    ALMACENERO("Almacenero");

    private String position;
    PositionEnum(String position) {
        this.position = position;
    }

    public String getPosition(){return this.position;}
}
