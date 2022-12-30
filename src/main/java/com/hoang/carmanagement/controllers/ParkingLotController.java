package com.hoang.carmanagement.controllers;

import com.hoang.carmanagement.entity.ParkingLot;
import com.hoang.carmanagement.models.ResponseObject;
import com.hoang.carmanagement.repo.ParkingLotRepo;
import com.hoang.carmanagement.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
