package com.hoang.carmanagement.controllers;

import com.hoang.carmanagement.dto.OwnerDTO;
import com.hoang.carmanagement.dto.VehicleDTO;
import com.hoang.carmanagement.entity.Pricing;
import com.hoang.carmanagement.entity.Vehicle;
import com.hoang.carmanagement.models.ResponseObject;
import com.hoang.carmanagement.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Optional<VehicleDTO> vehicleDTO = vehicleService.getVehicleDTOById(id);
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

    @PutMapping("/update")
    public ResponseEntity<ResponseObject> updateVehicle(@RequestBody VehicleDTO vehicleDTO){
        Optional<VehicleDTO> newVec = vehicleService.updateVehicle(vehicleDTO);
        return newVec.map(f->ResponseEntity.ok(new ResponseObject("OK", "update OK", f))).orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addVehicle(@RequestBody VehicleDTO vehicleDTO){
        VehicleDTO newVec = vehicleService.addVehicle(vehicleDTO);
        return ResponseEntity.ok(new ResponseObject("OK", "add OK", newVec));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseObject> deleteVehicle(@RequestParam Long id){
        if( vehicleService.deleteVehicle(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
