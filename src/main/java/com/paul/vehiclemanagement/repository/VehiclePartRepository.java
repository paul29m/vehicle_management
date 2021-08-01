package com.paul.vehiclemanagement.repository;

import com.paul.vehiclemanagement.domain.VehiclePart;
import com.paul.vehiclemanagement.domain.VehicleType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehiclePartRepository extends CrudRepository<VehiclePart, Long> {
    Optional<VehicleType> findByName(String typeName);
}
