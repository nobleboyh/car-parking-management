package com.hoang.carmanagement.controllers;

import com.hoang.carmanagement.entity.Floor;
import com.hoang.carmanagement.entity.Pricing;
import com.hoang.carmanagement.models.ResponseObject;
import com.hoang.carmanagement.services.FloorService;
import com.hoang.carmanagement.services.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(BaseController.BASE_URL + "/pricing")
public class PricingController extends BaseController{
    private PricingService pricingService;

    @Autowired
    public void setPricingService(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getAllPrices(){
        return ResponseEntity.ok(new ResponseObject("OK", "Get successfully", pricingService.getAllPricing()));
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseObject> getPriceById(@RequestParam Long id){
        Optional<Pricing> floor = pricingService.getPricingById(id);
        return floor.map(f->ResponseEntity.ok(new ResponseObject("OK", "Get OK", f))).orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseObject> updatePrice(@RequestBody Pricing pricing){
        Optional<Pricing> newPricing = pricingService.updatePricing(pricing);
        return newPricing.map(f->ResponseEntity.ok(new ResponseObject("OK", "Get OK", f))).orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addPricing(@RequestBody Pricing pricing){
        Pricing newPricing = pricingService.addPricing(pricing);
        return ResponseEntity.ok(new ResponseObject("OK", "Get OK", newPricing));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseObject> deletePricing(@RequestParam Long id){
        if( pricingService.deletePricing(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
