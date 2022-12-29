package com.hoang.carmanagement.test;

import com.hoang.carmanagement.entity.*;
import com.hoang.carmanagement.enums.VehicleSize;
import com.hoang.carmanagement.repo.*;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class InitDB implements CommandLineRunner {

    private FloorRepo floorRepo;
    private PricingRepo pricingRepo;
    private OwnerRepo ownerRepo;

    private VehicleRepo vehicleRepo;

    private ParkingLotRepo parkingLotRepo;

    @Autowired
    public void setFloorRepo(FloorRepo floorRepo) {
        this.floorRepo = floorRepo;
    }

    @Autowired
    public void setPricingRepo(PricingRepo pricingRepo) {
        this.pricingRepo = pricingRepo;
    }

    @Autowired
    public void setOwnerRepo(OwnerRepo ownerRepo) {
        this.ownerRepo = ownerRepo;
    }

    @Autowired
    public void setVehicleRepo(VehicleRepo vehicleRepo) {
        this.vehicleRepo = vehicleRepo;
    }

    @Autowired
    public void setParkingLotRepo(ParkingLotRepo parkingLotRepo) {
        this.parkingLotRepo = parkingLotRepo;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        //Create new Floor
        Floor floor1 = new Floor("B1");
        Floor floor2 = new Floor("B2");
        floorRepo.save(floor1);
        floorRepo.save(floor2);

        //Create Pricing
        Pricing pricing1 = new Pricing(1, 20 ,500);
        Pricing pricing2 = new Pricing(2, 40, 700);
        pricingRepo.save(pricing1);
        pricingRepo.save(pricing2);

        //Create owner
        Owner owner1  = new Owner("Mai Khanh Chi", true );
        Owner owner2 = new Owner("Hoang Bui", false);
        Owner owner3 = new Owner("Bui Mai An", true);
        ownerRepo.saveAll(List.of(owner1, owner2, owner3));

        //Create vehicle
        Vehicle vehicle1 = new Vehicle("30A1234", "Mazda", VehicleSize.SEAT_4);
        Vehicle vehicle2 = new Vehicle("29B1341", "Toyota", VehicleSize.SEAT_7);
        vehicleRepo.saveAll(List.of(vehicle1, vehicle2));
        vehicle1.setOwnerList(List.of(owner1, owner2));
        vehicle2.setOwnerList(List.of(owner1, owner2, owner3));

        //Create parking lot
        ParkingLot[] parkingLots = new ParkingLot[10];
        IntStream.range(0,parkingLots.length).
                forEach(i -> {
                            parkingLots[i] = new  ParkingLot(pricing1, floor1, "P" + i, new Timestamp(System.currentTimeMillis()));
                            parkingLotRepo.save(parkingLots[i]);
                        }
                );
        parkingLots[0].setVehicle(vehicle1);
    }
}
