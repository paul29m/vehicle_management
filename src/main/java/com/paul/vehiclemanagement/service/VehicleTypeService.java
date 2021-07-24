package com.paul.vehiclemanagement.service;

import com.paul.vehiclemanagement.model.VehicleTypeModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class VehicleTypeService implements IManagementService<VehicleTypeModel> {
    @Override
    public ResponseEntity create(VehicleTypeModel vehicleTypeModel) {
        return null;
    }

    @Override
    public List<VehicleTypeModel> getAll() {
        return null;
    }

    @Override
    public VehicleTypeModel getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity update(VehicleTypeModel vehicleTypeModel) {
        return null;
    }

    @Override
    public ResponseEntity delete(Long id) {
        return null;
    }
}
