package com.paul.vehiclemanagement.service;

import com.paul.vehiclemanagement.model.VehicleModel;
import com.paul.vehiclemanagement.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleManagementService implements IManagementService<VehicleModel> {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public ResponseEntity create(VehicleModel vehicleTypeModel) {
        return ResponseEntity.ok().build();
    }

    @Override
    public List<VehicleModel> getAll() {
        List<VehicleModel> result = new ArrayList<>();
        vehicleRepository.findAll().forEach(v -> result.add(new VehicleModel(v)));

        return result;
    }

    @Override
    public VehicleModel getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity update(VehicleModel vehicleTypeModel) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity delete(Long id) {
        return ResponseEntity.ok().build();
    }
}
