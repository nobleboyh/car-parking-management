package com.hoang.carmanagement.repo;

import com.hoang.carmanagement.entity.Pricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingRepo extends JpaRepository<Pricing, Long> {
}
