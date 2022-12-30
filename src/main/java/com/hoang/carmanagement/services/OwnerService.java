package com.hoang.carmanagement.services;

import com.hoang.carmanagement.dto.OwnerDTO;
import com.hoang.carmanagement.entity.Owner;
import com.hoang.carmanagement.entity.Vehicle;
import com.hoang.carmanagement.repo.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

    private OwnerRepo ownerRepo;

    @Autowired
    public void setOwnerRepo(OwnerRepo ownerRepo) {
        this.ownerRepo = ownerRepo;
    }

    public List<OwnerDTO> getALlOwners(){
        return this.ownerRepo.findAll().stream().map(OwnerService::convertToDTO).toList();
    }

    public Optional<OwnerDTO> getOwnerById(Long id){
        return ownerRepo.findById(id).map(OwnerService::convertToDTO);
    }

    public static OwnerDTO convertToDTO(Owner owner){
        return new OwnerDTO(
                owner.getId(),
                owner.getName(),
                owner.getPresident(),
                owner.getVehicleList().stream().map(Vehicle::getId).toList()
        );
    }
}
