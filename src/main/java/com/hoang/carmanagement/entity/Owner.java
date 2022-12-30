package com.hoang.carmanagement.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private Boolean isPresident;

    @ManyToMany(mappedBy = "ownerList", fetch = FetchType.LAZY)
    private List<Vehicle> vehicleList;

    public Owner(String name, Boolean isPresident) {
        this.name = name;
        this.isPresident = isPresident;
    }

    public Owner() {

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

    public Boolean getPresident() {
        return isPresident;
    }

    public void setPresident(Boolean president) {
        isPresident = president;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }
}
