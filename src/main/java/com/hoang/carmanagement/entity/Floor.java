package com.hoang.carmanagement.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "floor")
    private List<ParkingLot> parkingLotList;

    public Floor(String name) {
        this.name = name;
    }

    public Floor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
