package com.paul.vehiclemanagement.service;

import com.paul.vehiclemanagement.Utils.InvalidDataException;
import com.paul.vehiclemanagement.domain.VehiclePart;
import com.paul.vehiclemanagement.domain.VehicleType;
import com.paul.vehiclemanagement.model.VehicleTypeModel;
import com.paul.vehiclemanagement.repository.VehiclePartRepository;
import com.paul.vehiclemanagement.repository.VehicleTypeRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleTypeService implements IManagementService<VehicleTypeModel> {

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    private VehiclePartRepository vehiclePartRepository;

    @Override
    public List<VehicleTypeModel> getAll() {
        List<VehicleTypeModel> result = new ArrayList<>();
        vehicleTypeRepository.findAll().forEach(v -> result.add(new VehicleTypeModel(v)));
        return result;
    }

    @Override
    public Optional<VehicleTypeModel> getById(Long id) {
        if (id == null) {
            return Optional.of(VehicleTypeModel.builder().vehicleParts(new ArrayList<>()).build());
        }

        Optional<VehicleType> vehicleOptional = vehicleTypeRepository.findById(id);
        return Optional.ofNullable(vehicleOptional.map(VehicleTypeModel::new).orElse(null));
    }

    @Override
    public Optional<VehicleTypeModel> saveOrUpdate(VehicleTypeModel vehicleTypeModel) {
        if (vehicleTypeModel == null || vehicleTypeModel.getName() == null) {
            throw new InvalidDataException("Data entered is invalid.");
        }
        VehicleType vehicleType = vehicleTypeModel.getVehicleTypeId() != null ? vehicleTypeRepository.findById(vehicleTypeModel.getVehicleTypeId())
                .orElseGet(VehicleType::new) : new VehicleType();
        updateEntity(vehicleType, vehicleTypeModel);
        try {
            vehicleType = vehicleTypeRepository.save(vehicleType);
            vehicleTypeModel = new VehicleTypeModel(vehicleType);
        } catch (DataIntegrityViolationException e) {
            Throwable t = e.getCause();
            while ((t != null) && !(t instanceof ConstraintViolationException)) {
                t = t.getCause();
            }
            if (t instanceof ConstraintViolationException) {
                throw new InvalidDataException("Vehicle Type with given name already exists.");
            }
            throw new InvalidDataException("Data could not be saved.");
        }

        return Optional.of(vehicleTypeModel);
    }

    private void updateEntity(VehicleType vehicleType, VehicleTypeModel vehicleTypeModel) {
        vehicleType.setName(vehicleTypeModel.getName().toUpperCase());
        if (vehicleTypeModel.getVehiclePartsStringList() != null) {
            List<String> vehiclePartsNameList = Arrays.asList(vehicleTypeModel.getVehiclePartsStringList().split(","));
            if (!vehiclePartsNameList.isEmpty()) {
                vehicleType.setVehicleParts(new ArrayList<>());
                vehiclePartsNameList.forEach(name -> {
                    Optional<VehiclePart> part = vehiclePartRepository.findByName(name.toUpperCase().trim());
                    if (part.isPresent()) {
                        vehicleType.getVehicleParts().add(part.get());
                    } else {
                        throw new InvalidDataException("Can not save vehicle type. Part <" + name + "> is not valid.");
                    }
                });
            }
        }
    }

    @Override
    public Optional<VehicleTypeModel> delete(Long id) {
        if (id == null) {
            throw new InvalidDataException("Entry does not exist");
        }
        Optional<VehicleType> vehicleTypeOptional = vehicleTypeRepository.findById(id);
        try {
            vehicleTypeOptional.ifPresent(vehicleType -> vehicleTypeRepository.delete(vehicleType));
        } catch (DataIntegrityViolationException e) {
            throw new InvalidDataException("Can not delete vehicle type because are vehicles in the system with this type.");
        }
        return Optional.of(vehicleTypeOptional.map(vehicleType -> new VehicleTypeModel())
                .orElseThrow(() -> new InvalidDataException("Entry does not exist")));
    }
}


