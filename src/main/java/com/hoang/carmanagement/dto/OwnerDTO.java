package com.hoang.carmanagement.dto;


import com.hoang.carmanagement.entity.Vehicle;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;

import java.util.List;

public class OwnerDTO {
    private Long id;

    private String name;

    private Boolean isPresident;

    private List<Long> vehicleIdList;
}
