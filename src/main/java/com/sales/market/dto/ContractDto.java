package com.sales.market.dto;

import com.sales.market.model.Contract;
import com.sales.market.model.Employee;
import com.sales.market.model.Position;
import org.modelmapper.ModelMapper;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

public class ContractDto extends DtoBase<Contract> {
    private Long employeeId;
    private Long positionId;
    private Date initDate;
    private Date endDate;
    private boolean active;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
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