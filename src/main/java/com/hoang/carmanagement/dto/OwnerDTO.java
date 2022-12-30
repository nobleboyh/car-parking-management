package com.hoang.carmanagement.dto;


import com.hoang.carmanagement.entity.Vehicle;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;

import java.util.List;

public class OwnerDTO {
    private Long id;

    private String name;

    private Boolean isPresident;

    private List<Long> vehicleIdList;

    public OwnerDTO(Long id, String name, Boolean isPresident, List<Long> vehicleIdList) {
        this.id = id;
        this.name = name;
        this.isPresident = isPresident;
        this.vehicleIdList = vehicleIdList;
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

    public List<Long> getVehicleIdList() {
        return vehicleIdList;
    }

    public void setVehicleIdList(List<Long> vehicleIdList) {
        this.vehicleIdList = vehicleIdList;
    }
}
