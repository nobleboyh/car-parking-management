package com.hoang.carmanagement.services;

import com.hoang.carmanagement.dto.ParkingLotDTO;
import com.hoang.carmanagement.entity.ParkingLot;
import com.hoang.carmanagement.entity.Vehicle;
import com.hoang.carmanagement.repo.ParkingLotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotService {

    private ParkingLotRepo parkingLotRepo;

    private VehicleService vehicleService;

    @Autowired
    public void setParkingLotRepo(ParkingLotRepo parkingLotRepo) {
        this.parkingLotRepo = parkingLotRepo;
    }

    @Autowired
    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public List<ParkingLotDTO> getAllParkingLots(){
        return parkingLotRepo.findAll().stream().map(this::convertToDTO).toList();
    }

    public Optional<ParkingLot> getParkingLotById(Long id){
        return parkingLotRepo.findById(id);
    }

    public Optional<ParkingLotDTO> getParkingLotDTOById(Long id){
        return parkingLotRepo.findById(id).map(this::convertToDTO);
    }

    public boolean addVehicleToLot(Long lotId, Long vehicleId){
        Optional<ParkingLot> parkingLot = getParkingLotById(lotId);
        Optional<Vehicle> vehicle = vehicleService.getVehicleById(vehicleId);
        if (parkingLot.isPresent() && vehicle.isPresent()) {
            parkingLot.get().setVehicle(vehicle.get());
            parkingLotRepo.saveAndFlush(parkingLot.get());
            return true;
        }
        return false;
    }

    public void checkoutVehicleFromLot(Long id){
        Optional<ParkingLot> parkingLot = getParkingLotById(id);
        parkingLot.ifPresent(
                l -> {
                    l.setVehicle(null);
                    parkingLotRepo.save(l);
                }
        );
    }

    public Duration getOccupiedTime(Long id){
        return parkingLotRepo.calculateOccupiedTime(id);
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
