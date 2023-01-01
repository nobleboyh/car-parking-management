package com.hoang.carmanagement.controllers;

import com.hoang.carmanagement.dto.OwnerDTO;
import com.hoang.carmanagement.models.ResponseObject;
import com.hoang.carmanagement.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(BaseController.BASE_URL + "/owner")
public class OwnerController extends BaseController{
    private OwnerService ownerService;

    @Autowired
    public void setOwnerService(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getAllOwners(){
        return ResponseEntity.ok(new ResponseObject("OK", "Get owners successfully",
                ownerService.getALlOwners()));
    }

    @GetMapping("/find")
    public ResponseEntity<ResponseObject> getOwnerById(@RequestParam Long id){
        Optional<OwnerDTO> ownerDTO = ownerService.getOwnerById(id);
        if(ownerDTO.isPresent()){
            return ResponseEntity.ok(new ResponseObject("OK","Get owners successfully",
                    ownerService.getOwnerById(id)));
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseObject> updateOwner(@RequestBody OwnerDTO ownerDTO){
        Optional<OwnerDTO> newVec = ownerService.updateOwner(ownerDTO);
        return newVec.map(f->ResponseEntity.ok(new ResponseObject("OK", "update OK", f))).orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addOwner(@RequestBody OwnerDTO ownerDTO){
        OwnerDTO newVec = ownerService.addOwner(ownerDTO);
        return ResponseEntity.ok(new ResponseObject("OK", "add OK", newVec));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseObject> deleteOwner(@RequestParam Long id){
        if( ownerService.deleteOwner(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
