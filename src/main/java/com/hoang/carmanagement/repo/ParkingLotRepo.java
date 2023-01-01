package com.hoang.carmanagement.repo;

import com.hoang.carmanagement.entity.Floor;
import com.hoang.carmanagement.entity.ParkingLot;
import com.hoang.carmanagement.entity.Pricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.List;

@Repository
public interface ParkingLotRepo extends JpaRepository<ParkingLot, Long> {

    @Query("SELECT CURRENT_TIMESTAMP - c.occupiedTime as time from ParkingLot c where c.id = ?1 and c.occupiedTime is not null")
    Duration calculateOccupiedTime(Long id);

    @Modifying
    @Query("UPDATE ParkingLot p set p.occupiedTime = CURRENT_TIMESTAMP WHERE p.id = :id")
    void updateCurrentOccupiedTime(@Param("id") Long id);

    List<ParkingLot> findAllByFloor(Floor floor);

    List<ParkingLot> findAllByPricing(Pricing pricing);

    @Query("SELECT p FROM ParkingLot p where p.vehicle.id = ?1")
    List<ParkingLot> findAllByVehicleId(Long vehicleId);
}
