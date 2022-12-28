package com.hoang.carmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pricing {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Integer perHour;

    private Integer perDay;

    private Integer perMonth;

    public Pricing(Integer perHour, Integer perDay, Integer perMonth) {
        this.perHour = perHour;
        this.perDay = perDay;
        this.perMonth = perMonth;
    }

    public Pricing() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPerHour() {
        return perHour;
    }

    public void setPerHour(Integer perHour) {
        this.perHour = perHour;
    }

    public Integer getPerDay() {
        return perDay;
    }

    public void setPerDay(Integer perDay) {
        this.perDay = perDay;
    }

    public Integer getPerMonth() {
        return perMonth;
    }

    public void setPerMonth(Integer perMonth) {
        this.perMonth = perMonth;
    }
}
