package com.hoang.carmanagement.services;

import com.hoang.carmanagement.entity.Floor;
import com.hoang.carmanagement.repo.FloorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FloorService {

    private FloorRepo floorRepo;

    @Autowired
    public void setFloorRepo(FloorRepo floorRepo) {
        this.floorRepo = floorRepo;
    }

    public List<Floor> getAllFloors(){
        return floorRepo.findAll();
    }

    public Optional<Floor> getFloorById(Long id){
        if (id == null){
            return Optional.empty();
        }
        return floorRepo.findById(id);
    }

    public Optional<Floor> updateFloor(Floor floor){
        Optional<Floor> existFloor = this.getFloorById(floor.getId());
        existFloor.ifPresent(value -> floorRepo.save(value));
        return existFloor;
    }
}
