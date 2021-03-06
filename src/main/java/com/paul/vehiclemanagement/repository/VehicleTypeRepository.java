package com.paul.vehiclemanagement.repository;

import com.paul.vehiclemanagement.domain.VehicleType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleTypeRepository extends CrudRepository<VehicleType, Long> {
    Optional<VehicleType> findByName(String typeName);
}
