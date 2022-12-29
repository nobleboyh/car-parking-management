package com.hoang.carmanagement.repo;

import com.hoang.carmanagement.entity.ParkingLot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepo extends CrudRepository<ParkingLot, Long> {
}
