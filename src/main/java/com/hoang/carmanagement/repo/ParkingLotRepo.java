package com.hoang.carmanagement.repo;

import com.hoang.carmanagement.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public interface ParkingLotRepo extends JpaRepository<ParkingLot, Long> {

    @Query("SELECT CURRENT_TIMESTAMP - c.occupiedTime as time from ParkingLot c where c.id = ?1 and c.occupiedTime is not null")
    Duration calculateOccupiedTime(Long id);
}
