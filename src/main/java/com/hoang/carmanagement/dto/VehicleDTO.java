package com.hoang.carmanagement.dto;

import com.hoang.carmanagement.entity.Owner;
import com.hoang.carmanagement.enums.VehicleSize;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.List;

public class VehicleDTO {
    private Long id;

    private String licensePlate;

    private String brand;

    private VehicleSize size;

    private List<Long> ownerIdList;

    public VehicleDTO(Long id, String licensePlate, String brand, VehicleSize size, List<Long> ownerIdList) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.size = size;
        this.ownerIdList = ownerIdList;
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

    public List<Long> getOwnerIdList() {
        return ownerIdList;
    }

    public void setOwnerIdList(List<Long> ownerIdList) {
        this.ownerIdList = ownerIdList;
    }
}
