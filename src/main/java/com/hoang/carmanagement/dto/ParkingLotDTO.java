package com.hoang.carmanagement.dto;

import com.hoang.carmanagement.entity.Floor;
import com.hoang.carmanagement.entity.Pricing;
import com.hoang.carmanagement.entity.Vehicle;
import jakarta.persistence.*;

import java.sql.Timestamp;

public class ParkingLotDTO {
    private Long id;

    private Pricing pricing;

    private Floor floor;

    private Long vehicleId;

    private String name;

    private Timestamp occupiedTime;

    public ParkingLotDTO(Long id, Pricing pricing, Floor floor, Long vehicleId, String name, Timestamp occupiedTime) {
        this.id = id;
        this.pricing = pricing;
        this.floor = floor;
        this.vehicleId = vehicleId;
        this.name = name;
        this.occupiedTime = occupiedTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pricing getPricing() {
        return pricing;
    }

    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getOccupiedTime() {
        return occupiedTime;
    }

    public void setOccupiedTime(Timestamp occupiedTime) {
        this.occupiedTime = occupiedTime;
    }
}
