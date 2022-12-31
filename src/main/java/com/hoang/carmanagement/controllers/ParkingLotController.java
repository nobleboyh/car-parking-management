package com.hoang.carmanagement.controllers;

import com.hoang.carmanagement.entity.ParkingLot;
import com.hoang.carmanagement.models.ResponseObject;
import com.hoang.carmanagement.repo.ParkingLotRepo;
import com.hoang.carmanagement.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(BaseController.BASE_URL + "/lot")
public class ParkingLotController extends BaseController {

    private ParkingLotService parkingLotService;

    @Autowired
    public void setParkingLotService(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getAllParkingLot(){
        return  ResponseEntity.ok(
                new ResponseObject("OK","Get parking lot list successfully",
                        parkingLotService.getAllParkingLots()));
    }

    @PutMapping("/add-vehicle")
    public ResponseEntity<ResponseObject> addVehicleToLot(@RequestParam(name = "lotid") Long lotId, @RequestParam(name = "vid") Long vehicleId){
        if (parkingLotService.addVehicleToLot(lotId, vehicleId)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body( new ResponseObject("Fail", "No vehicle or Lot found", Optional.empty()));
    }

    @PutMapping("/checkout-vehicle")
    public ResponseEntity<ResponseObject> checkoutVehicleFromLot(@RequestParam(name = "lotid") Long lotId){
        parkingLotService.checkoutVehicleFromLot(lotId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-time")
    public ResponseEntity<ResponseObject> getOccupiedTime(@RequestParam(name = "lotid") Long lotId){
        return ResponseEntity.ok(new ResponseObject("OK", "Get time in millis", parkingLotService.getOccupiedTime(lotId).toMillis()));
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<String> handleException(Exception exception){
        System.out.println("Fuck! Error occurred");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }
}
