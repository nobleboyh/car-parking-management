package com.hoang.carmanagement.repo;

import com.hoang.carmanagement.entity.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorRepo extends JpaRepository<Floor, Long> {
}
