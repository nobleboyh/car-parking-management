package com.hoang.carmanagement.services;

import com.hoang.carmanagement.dto.ParkingLotDTO;
import com.hoang.carmanagement.entity.ParkingLot;
import com.hoang.carmanagement.entity.Vehicle;
import com.hoang.carmanagement.repo.ParkingLotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotService {

    private ParkingLotRepo parkingLotRepo;

    @Autowired
    public void setParkingLotRepo(ParkingLotRepo parkingLotRepo) {
        this.parkingLotRepo = parkingLotRepo;
    }

    public List<ParkingLotDTO> getAllParkingLots(){
        return parkingLotRepo.findAll().stream().map(this::convertToDTO).toList();
    }

    private ParkingLotDTO convertToDTO(ParkingLot parkingLot){
        return new ParkingLotDTO(
                parkingLot.getId(),
                parkingLot.getPricing(),
                parkingLot.getFloor(),
                Optional.ofNullable(parkingLot.getVehicle()).map(Vehicle::getId).orElse(null),
                parkingLot.getName(),
                parkingLot.getOccupiedTime()
        );
    }
}
