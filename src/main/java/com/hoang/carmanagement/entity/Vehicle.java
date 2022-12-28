package com.hoang.carmanagement.entity;


import com.hoang.carmanagement.enums.VehicleSize;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String licensePlate;

    private String brand;

    private VehicleSize size;

    @ManyToMany
    @JoinTable(name = "vehicle_owner",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "owner_id"))
    private List<Owner> ownerList;

    private Long lotId;

    public Vehicle() {
    }

    public Vehicle(String licensePlate, String brand, VehicleSize size, List<Owner> ownerList, Long lotId) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.size = size;
        this.ownerList = ownerList;
        this.lotId = lotId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public VehicleSize getSize() {
        return size;
    }

    public void setSize(VehicleSize size) {
        this.size = size;
    }

    public List<Owner> getOwnerList() {
        return ownerList;
    }

    public void setOwnerList(List<Owner> ownerList) {
        this.ownerList = ownerList;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }
}
