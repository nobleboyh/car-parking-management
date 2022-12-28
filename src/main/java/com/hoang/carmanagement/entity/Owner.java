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

    @ManyToMany
    @JoinTable(name = "vehicle_owner",
            joinColumns = @JoinColumn(name = "owner_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private List<Vehicle> vehicleList;

    public Owner(String name, Boolean isPresident, List<Vehicle> vehicleList) {
        this.name = name;
        this.isPresident = isPresident;
        this.vehicleList = vehicleList;
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
