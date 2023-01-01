package com.hoang.carmanagement.services;

import com.hoang.carmanagement.dto.ParkingLotDTO;
import com.hoang.carmanagement.entity.ParkingLot;
import com.hoang.carmanagement.entity.Pricing;
import com.hoang.carmanagement.entity.Vehicle;
import com.hoang.carmanagement.repo.ParkingLotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.swing.text.html.Option;
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

    @Transactional
    public boolean addVehicleToLot(Long lotId, Long vehicleId){
        Optional<ParkingLot> parkingLot = getParkingLotById(lotId);
        Optional<Vehicle> vehicle = vehicleService.getVehicleById(vehicleId);
        if (parkingLot.isPresent() && vehicle.isPresent()) {
            parkingLot.get().setVehicle(vehicle.get());
            parkingLotRepo.updateCurrentOccupiedTime(parkingLot.get().getId());

            return true;
        }
        return false;
    }

    public Long pricingLot(Long id){
        Optional<Duration> duration = Optional.ofNullable(this.getOccupiedTime(id));
        Optional<ParkingLot> parkingLot = getParkingLotById(id);
        if (duration.isPresent() && parkingLot.isPresent()){
            Duration realDuration = duration.get();
            ParkingLot realParkingLot = parkingLot.get();
            Pricing pricing = realParkingLot.getPricing();
            if(realDuration.toHours() == 0){
                //< 1h
                return Long.valueOf(pricing.getPerHour());
            } else if (realDuration.toHours() >0 && realDuration.toDays() == 0) {
                //< 1 day
                return pricing.getPerHour()*realDuration.toHours();
            } else if (realDuration.toDays() > 0 && realDuration.toDays() <= 30) {  //>30: Monthly
                //< 1 month
                return pricing.getPerDay() * realDuration.toDays() +
                        pricing.getPerHour() * realDuration.toHours() & 24;   //24h/1day
            } else{
                // > 1 month
                return pricing.getPerMonth() * (realDuration.toDays() / 30)
                        + pricing.getPerDay()* (realDuration.toDays() % 30);
            }
        }
        return null;
    }

    public void checkoutVehicleFromLot(Long id){
        Optional<ParkingLot> parkingLot = getParkingLotById(id);
        parkingLot.ifPresent(
                l -> {
                    l.setVehicle(null);
                    l.setOccupiedTime(null);
                    parkingLotRepo.save(l);
                }
        );
    }

    public Duration getOccupiedTime(Long id){
        return parkingLotRepo.calculateOccupiedTime(id);
    }

    @Transactional
    public ParkingLotDTO saveParkinglot(ParkingLotDTO parkingLotDTO){
        ParkingLot parkingLot = convertToEntity(parkingLotDTO);
        parkingLotRepo.saveAndFlush(parkingLot);
        return convertToDTO(parkingLot);
    }

    @Transactional
    public Optional<ParkingLotDTO> updateParkingLot(ParkingLotDTO parkingLot){
        Optional<ParkingLot> existLot = getParkingLotById(parkingLot.getId());
        if (existLot.isPresent()){
            existLot.get().setFloor(parkingLot.getFloor());
            existLot.get().setOccupiedTime(parkingLot.getOccupiedTime());
            existLot.get().setPricing(parkingLot.getPricing());
            existLot.get().setName(parkingLot.getName());
            Optional<Vehicle> vehicle = vehicleService.getVehicleById(parkingLot.getId());
            vehicle.ifPresent(existLot.get()::setVehicle);

            return Optional.of(convertToDTO(existLot.get()));
        }
        return Optional.empty();
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

    private ParkingLot convertToEntity(ParkingLotDTO parkingLotDTO){
        ParkingLot parkingLot = new ParkingLot(
                parkingLotDTO.getId(),
                parkingLotDTO.getPricing(),
                parkingLotDTO.getFloor(),
                parkingLotDTO.getName(),
                parkingLotDTO.getOccupiedTime()
        );
        Optional<Vehicle> vehicle = vehicleService.getVehicleById(parkingLotDTO.getVehicleId());
        vehicle.ifPresent(parkingLot::setVehicle);
        return  parkingLot;
    }


}
