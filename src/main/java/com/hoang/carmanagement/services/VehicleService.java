package com.hoang.carmanagement.services;

import com.hoang.carmanagement.dto.OwnerDTO;
import com.hoang.carmanagement.dto.VehicleDTO;
import com.hoang.carmanagement.entity.Owner;
import com.hoang.carmanagement.entity.Vehicle;
import com.hoang.carmanagement.repo.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private VehicleRepo vehicleRepo;

    @Autowired
    public void setVehicleRepo(VehicleRepo vehicleRepo) {
        this.vehicleRepo = vehicleRepo;
    }

    public List<VehicleDTO> getAllVehicles(){
        return vehicleRepo.findAll().stream().map(this::convertToDTO).toList();
    }

    public Optional<Vehicle> getVehicleById(Long id){
         return vehicleRepo.findById(id);
    }

    public Optional<VehicleDTO> getVehicleDTOById(Long id){
        return vehicleRepo.findById(id).map(this::convertToDTO);
    }

    public List<OwnerDTO> getOwnerLists(Vehicle vehicle){
        return vehicle.getOwnerList().stream().map(OwnerService::convertToDTO).toList();
    }

    public List<OwnerDTO> getOwnerLists(Long vehicleId){
        Optional<Vehicle> vehicle = vehicleRepo.findById(vehicleId);
        return vehicle.map(this::getOwnerLists).orElse(Collections.emptyList());
    }

    private VehicleDTO convertToDTO(Vehicle vehicle){
        return new VehicleDTO(
                vehicle.getId(),
                vehicle.getLicensePlate(),
                vehicle.getBrand(),
                vehicle.getSize(),
                vehicle.getOwnerList().stream().map(Owner::getId).toList()
        );
    }
}
