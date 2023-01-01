package com.hoang.carmanagement.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "price_id")
    private Pricing pricing;

    @ManyToOne
    @JoinColumn(name = "floor_id")
    private Floor floor;

    @OneToOne
    @JoinColumn(name = "vehicle_id", unique = true)
    private Vehicle vehicle;

    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp occupiedTime;

    public ParkingLot() {
    }

    public ParkingLot(Pricing pricing, Floor floor, Vehicle vehicle, String name, Timestamp occupiedTime) {
        this.pricing = pricing;
        this.floor = floor;
        this.vehicle = vehicle;
        this.name = name;
        this.occupiedTime = occupiedTime;
    }

    public ParkingLot(Pricing pricing, Floor floor, String name, Timestamp occupiedTime) {
        this.pricing = pricing;
        this.floor = floor;
        this.name = name;
        this.occupiedTime = occupiedTime;
    }

    public ParkingLot(Long id,Pricing pricing, Floor floor, String name, Timestamp occupiedTime) {
        this.id = id;
        this.pricing = pricing;
        this.floor = floor;
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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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
