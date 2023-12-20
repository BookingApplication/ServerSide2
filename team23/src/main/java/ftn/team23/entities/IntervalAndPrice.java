package ftn.team23.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

public class IntervalAndPrice implements Serializable {
    private Long startDate;
    private Long endDate;
    private Double price;

    public IntervalAndPrice(){}

    public IntervalAndPrice(Long startDate, Long endDate, Double price) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
