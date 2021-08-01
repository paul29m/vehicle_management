package com.paul.vehiclemanagement.service;

import com.paul.vehiclemanagement.Utils.InvalidDataException;
import com.paul.vehiclemanagement.domain.VehiclePart;
import com.paul.vehiclemanagement.model.VehiclePartModel;
import com.paul.vehiclemanagement.repository.VehiclePartRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        if (vehiclePartModel == null || vehiclePartModel.getName() == null) {
            throw new InvalidDataException("Data entered is invalid.");
        }
        VehiclePart vehiclePart = vehiclePartModel.getVehiclePartId() != null ? vehiclePartRepository.findById(vehiclePartModel.getVehiclePartId())
                .orElseGet(VehiclePart::new) : new VehiclePart();
        vehiclePart.setName(vehiclePartModel.getName().toUpperCase());
        try {
            vehiclePart = vehiclePartRepository.save(vehiclePart);
            vehiclePartModel = new VehiclePartModel(vehiclePart);
        } catch (DataIntegrityViolationException e) {
            Throwable t = e.getCause();
            while ((t != null) && !(t instanceof ConstraintViolationException)) {
                t = t.getCause();
            }
            if (t instanceof ConstraintViolationException) {
                throw new InvalidDataException("Vehicle Part with given name already exists.");
            }
            throw new InvalidDataException("Data could not be saved.");
        }

        return Optional.of(vehiclePartModel);
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
