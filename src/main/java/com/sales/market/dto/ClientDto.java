package com.sales.market.dto;

import com.sales.market.model.Client;
import com.sales.market.model.Sale;
import org.modelmapper.ModelMapper;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

public class ClientDto extends DtoBase<Client> {
    private String name;
    private String lastName;
    private String cellphone;
    private String email;
    private int points;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}