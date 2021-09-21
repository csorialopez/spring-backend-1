/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.model;

import com.sales.market.dto.ContractDto;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class Contract extends ModelBase<ContractDto> {
    @ManyToOne(optional = false)
    private Employee employee;
    @OneToOne(optional = true)
    private Position position;
    private Date initDate;
    private Date endDate;
    private boolean active;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
