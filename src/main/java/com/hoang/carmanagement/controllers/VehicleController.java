package com.hoang.carmanagement.controllers;

import com.hoang.carmanagement.dto.OwnerDTO;
import com.hoang.carmanagement.dto.VehicleDTO;
import com.hoang.carmanagement.entity.Vehicle;
import com.hoang.carmanagement.models.ResponseObject;
import com.hoang.carmanagement.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/find")
    public ResponseEntity<ResponseObject> getVehicleById(@RequestParam Long id){
        Optional<VehicleDTO> vehicleDTO = vehicleService.getVehicleById(id);
        if(vehicleDTO.isPresent()){
            return ResponseEntity.ok(new ResponseObject("OK","Get vehicles successfully",
                    vehicleService.getVehicleById(id)));
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/find-owner")
    public ResponseEntity<ResponseObject> getVehicleOwnersById(@RequestParam Long id){
        List<OwnerDTO> vehicleDTOList = vehicleService.getOwnerLists(id);
        if(!vehicleDTOList.isEmpty()){
            return ResponseEntity.ok(new ResponseObject("OK","Get owners successfully",
                    vehicleDTOList));
        }else{
            return ResponseEntity.noContent().build();
        }
    }
}
