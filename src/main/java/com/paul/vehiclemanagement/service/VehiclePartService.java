package com.paul.vehiclemanagement.service;

import com.paul.vehiclemanagement.model.VehiclePartModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class VehiclePartService implements IManagementService<VehiclePartModel> {
    @Override
    public ResponseEntity create(VehiclePartModel vehicleTypeModel) {
        return ResponseEntity.ok().build();
    }

    @Override
    public List<VehiclePartModel> getAll() {
        return null;
    }

    @Override
    public VehiclePartModel getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity update(VehiclePartModel vehicleTypeModel) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity delete(Long id) {
        return ResponseEntity.ok().build();
    }
}
