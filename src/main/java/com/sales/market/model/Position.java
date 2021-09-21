/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.model;

import javax.persistence.Entity;

/*Cajero, Acomodador, Almacenero*/
@Entity
public class Position extends ModelBase {
    private String name;
    public Position() {

    }
    public Position(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
