package com.paul.vehiclemanagement.service;

import com.paul.vehiclemanagement.model.VehicleTypeModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class VehicleTypeService implements IManagementService<VehicleTypeModel> {

    @Override
    public List<VehicleTypeModel> getAll() {
        return null;
    }

    @Override
    public Optional<VehicleTypeModel> getById(Long id) {
        return null;
    }

    @Override
    public Optional<VehicleTypeModel> saveOrUpdate(VehicleTypeModel vehicleTypeModel) {
        return null;
    }

    @Override
    public Optional<VehicleTypeModel> delete(Long id) {
        return null;
    }
}
