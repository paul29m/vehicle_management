package com.paul.vehiclemanagement.repository;

import com.paul.vehiclemanagement.domain.VehicleType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepository extends CrudRepository<VehicleType, Long> {
}
