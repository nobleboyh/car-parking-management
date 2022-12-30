package com.hoang.carmanagement.controllers;

import com.hoang.carmanagement.models.ResponseObject;
import com.hoang.carmanagement.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BaseController.BASE_URL + "/vehicle")
public class VehicleController extends BaseController{

    private VehicleService vehicleService;

    @Autowired
    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getAllVehicles(){
        return ResponseEntity.ok(new ResponseObject("OK", "Get vehicles successfully",
                vehicleService.getAllVehicles()));
    }
}
