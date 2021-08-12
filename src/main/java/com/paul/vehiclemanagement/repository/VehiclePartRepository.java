package com.paul.vehiclemanagement.repository;

import com.paul.vehiclemanagement.domain.VehiclePart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehiclePartRepository extends CrudRepository<VehiclePart, Long> {
    Optional<VehiclePart> findByName(String typeName);
}
