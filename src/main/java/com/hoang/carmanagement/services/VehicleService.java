package com.hoang.carmanagement.services;

import com.hoang.carmanagement.dto.OwnerDTO;
import com.hoang.carmanagement.dto.VehicleDTO;
import com.hoang.carmanagement.entity.Owner;
import com.hoang.carmanagement.entity.ParkingLot;
import com.hoang.carmanagement.entity.Vehicle;
import com.hoang.carmanagement.repo.OwnerRepo;
import com.hoang.carmanagement.repo.ParkingLotRepo;
import com.hoang.carmanagement.repo.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private VehicleRepo vehicleRepo;

    private OwnerRepo ownerRepo;

    private ParkingLotRepo parkingLotRepo;

    @Autowired
    public void setVehicleRepo(VehicleRepo vehicleRepo) {
        this.vehicleRepo = vehicleRepo;
    }

    @Autowired
    public void setOwnerRepo(OwnerRepo ownerRepo) {
        this.ownerRepo = ownerRepo;
    }

    @Autowired
    public void setParkingLotRepo(ParkingLotRepo parkingLotRepo) {
        this.parkingLotRepo = parkingLotRepo;
    }

    public List<VehicleDTO> getAllVehicles(){
        return vehicleRepo.findAll().stream().map(this::convertToDTO).toList();
    }

    public Optional<Vehicle> getVehicleById(Long id){
        if (id == null){
            return Optional.empty();
        }
         return vehicleRepo.findById(id);
    }

    public Optional<VehicleDTO> getVehicleDTOById(Long id){
        if (id == null){
            return Optional.empty();
        }
        return vehicleRepo.findById(id).map(this::convertToDTO);
    }

    public List<OwnerDTO> getOwnerLists(Vehicle vehicle){
        return vehicle.getOwnerList().stream().map(new OwnerService()::convertToDTO).toList();
    }

    public List<OwnerDTO> getOwnerLists(Long vehicleId){
        Optional<Vehicle> vehicle = vehicleRepo.findById(vehicleId);
        return vehicle.map(this::getOwnerLists).orElse(Collections.emptyList());
    }

    public VehicleDTO addVehicle(VehicleDTO vehicleDTO){
        Vehicle vehicle = convertToEntity(vehicleDTO);
        vehicleRepo.save(vehicle);
        return convertToDTO(vehicle);
    }

    public Optional<VehicleDTO> updateVehicle(VehicleDTO vehicleDTO){
        Optional<Vehicle> vehicle = getVehicleById(vehicleDTO.getId());
        if(vehicle.isPresent()){
            Vehicle newVehicle = convertToEntity(vehicleDTO);
            vehicleRepo.save(newVehicle);
            return Optional.of(convertToDTO(newVehicle));
        }
        return Optional.empty();
    }

    @Transactional
    public boolean deleteVehicle(Long id){
        Optional<Vehicle> vehicle = getVehicleById(id);
        if (vehicle.isPresent()){
            List<ParkingLot> parkingLots = parkingLotRepo.findAllByVehicleId(id);
            parkingLots.forEach(p -> p.setVehicle(null));
            vehicleRepo.delete(vehicle.get());
            return true;
        }
        return false;
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

    private Vehicle convertToEntity(VehicleDTO vehicleDTO){
        Vehicle vehicle = new Vehicle(
            vehicleDTO.getId(),
            vehicleDTO.getLicensePlate(),
            vehicleDTO.getBrand(),
            vehicleDTO.getSize()
        );
        if (!vehicleDTO.getOwnerIdList().isEmpty()){
            List<Owner> owners = ownerRepo.findAllById(vehicleDTO.getOwnerIdList());
            vehicle.setOwnerList(owners);
        }

        return vehicle;
    }
}
