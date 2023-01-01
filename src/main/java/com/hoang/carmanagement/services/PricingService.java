package com.hoang.carmanagement.services;

import com.hoang.carmanagement.entity.ParkingLot;
import com.hoang.carmanagement.entity.Pricing;
import com.hoang.carmanagement.repo.ParkingLotRepo;
import com.hoang.carmanagement.repo.PricingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PricingService {

    private PricingRepo pricingRepo;

    private ParkingLotRepo parkingLotRepo;

    @Autowired
    public void setPricingRepo(PricingRepo pricingRepo) {
        this.pricingRepo = pricingRepo;
    }

    @Autowired
    public void setParkingLotService(ParkingLotRepo parkingLotRepo) {
        this.parkingLotRepo = parkingLotRepo;
    }

    public List<Pricing> getAllPricing(){
        return pricingRepo.findAll();
    }

    public Optional<Pricing> getPricingById(Long id){
        if (id == null){
            return Optional.empty();
        }
        return pricingRepo.findById(id);
    }

    public Pricing addPricing(Pricing pricing){
        pricingRepo.saveAndFlush(pricing);
        return pricing;
    }

    public Optional<Pricing> updatePricing(Pricing pricing){
        Optional<Pricing> existPricing = getPricingById(pricing.getId());
        if (existPricing.isPresent()){
            pricingRepo.saveAndFlush(pricing);
            return Optional.of(pricing);
        }
        return Optional.empty();
    }

    @Transactional
    public boolean deletePricing(Long id){
        Optional<Pricing> existPricing = getPricingById(id);
        if (existPricing.isPresent()){
            List<ParkingLot> parkingLots = parkingLotRepo.findAllByPricing(existPricing.get());
            parkingLots.forEach(p -> p.setPricing(null));
            pricingRepo.delete(existPricing.get());
            return true;

        }
        return false;
    }
}
