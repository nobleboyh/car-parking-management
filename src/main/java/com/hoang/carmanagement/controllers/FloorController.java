package com.hoang.carmanagement.controllers;

import com.hoang.carmanagement.entity.Floor;
import com.hoang.carmanagement.models.ResponseObject;
import com.hoang.carmanagement.services.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(BaseController.BASE_URL + "/floor")
public class FloorController extends BaseController{

    private FloorService floorService;

    @Autowired
    public void setFloorService(FloorService floorService) {
        this.floorService = floorService;
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getAllFloors(){
        return ResponseEntity.ok(new ResponseObject("OK", "Get successfully", floorService.getAllFloors()));
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseObject> getFloorById(@RequestParam Long id){
        Optional<Floor> floor = floorService.getFloorById(id);
        return floor.map(f->ResponseEntity.ok(new ResponseObject("OK", "Get OK", f))).orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseObject> updateFloor(@RequestBody Floor floor){
        Optional<Floor> newFloor = floorService.updateFloor(floor);
        return newFloor.map(f->ResponseEntity.ok(new ResponseObject("OK", "Get OK", f))).orElse(ResponseEntity.badRequest().build());
    }

}
