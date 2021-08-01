package com.paul.vehiclemanagement.service;

import com.paul.vehiclemanagement.Utils.InvalidDataException;
import com.paul.vehiclemanagement.domain.VehiclePart;
import com.paul.vehiclemanagement.model.VehiclePartModel;
import com.paul.vehiclemanagement.repository.VehiclePartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehiclePartService implements IManagementService<VehiclePartModel> {

    @Autowired
    private VehiclePartRepository vehiclePartRepository;

    @Override
    public List<VehiclePartModel> getAll() {
        List<VehiclePartModel> result = new ArrayList<>();
        vehiclePartRepository.findAll().forEach(v -> result.add(new VehiclePartModel(v)));
        return result;
    }

    @Override
    public Optional<VehiclePartModel> getById(Long id) {
        if (id == null) {
            return Optional.of(new VehiclePartModel());
        }

        Optional<VehiclePart> vehicleOptional = vehiclePartRepository.findById(id);
        return Optional.ofNullable(vehicleOptional.map(VehiclePartModel::new).orElse(null));

    }

    @Override
    public Optional<VehiclePartModel> saveOrUpdate(VehiclePartModel vehiclePartModel) {
        return null;
    }

    @Override
    public Optional<VehiclePartModel> delete(Long id) {
        if(id == null){
            throw new InvalidDataException("Entry does not exist");
        }
        Optional<VehiclePart> vehicleOptional = vehiclePartRepository.findById(id);
        vehiclePartRepository.deleteById(id);

        return Optional.of(vehicleOptional.map(vehiclePart -> new VehiclePartModel())
                .orElseThrow(() -> new InvalidDataException("Entry does not exist")));
    }
}
