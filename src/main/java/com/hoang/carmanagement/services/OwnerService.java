package com.hoang.carmanagement.services;

import com.hoang.carmanagement.dto.OwnerDTO;
import com.hoang.carmanagement.dto.VehicleDTO;
import com.hoang.carmanagement.entity.Owner;
import com.hoang.carmanagement.entity.Vehicle;
import com.hoang.carmanagement.repo.OwnerRepo;
import com.hoang.carmanagement.repo.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

    private OwnerRepo ownerRepo;

    private VehicleRepo vehicleRepo;

    @Autowired
    public void setOwnerRepo(OwnerRepo ownerRepo) {
        this.ownerRepo = ownerRepo;
    }

    @Autowired
    public void setVehicleRepo(VehicleRepo vehicleRepo) {
        this.vehicleRepo = vehicleRepo;
    }

    public List<OwnerDTO> getALlOwners(){
        return this.ownerRepo.findAll().stream().map(this::convertToDTO).toList();
    }

    public Optional<OwnerDTO> getOwnerById(Long id){
        return ownerRepo.findById(id).map(this::convertToDTO);
    }

    public OwnerDTO addOwner(OwnerDTO ownerDTO){
        Owner owner = convertToEntity(ownerDTO);
        ownerRepo.save(owner);
        return convertToDTO(owner);
    }

    public Optional<OwnerDTO> updateOwner(OwnerDTO ownerDTO){
        Optional<OwnerDTO> owner = getOwnerById(ownerDTO.getId());
        if(owner.isPresent()){
            Owner newOwner = convertToEntity(ownerDTO);
            ownerRepo.save(newOwner);
            return Optional.of(convertToDTO(newOwner));
        }
        return Optional.empty();
    }

    public boolean deleteOwner(Long id){
        Optional<OwnerDTO> ownerDTO = getOwnerById(id);
        if(ownerDTO.isPresent()){
            ownerRepo.delete(convertToEntity(ownerDTO.get()));
            return true;
        }
        return true;
    }

    public OwnerDTO convertToDTO(Owner owner){
        return new OwnerDTO(
                owner.getId(),
                owner.getName(),
                owner.getPresident(),
                owner.getVehicleList().stream().map(Vehicle::getId).toList()
        );
    }

    public Owner convertToEntity(OwnerDTO ownerDTO){
        Owner owner = new Owner(
                ownerDTO.getId(),
                ownerDTO.getName(),
                ownerDTO.getPresident()
        );
        if(!ownerDTO.getVehicleIdList().isEmpty()){
            List<Vehicle> vehicles = vehicleRepo.findAllById(ownerDTO.getVehicleIdList());
            owner.setVehicleList(vehicles);
        }
        return owner;
    }
}
