package com.paul.vehiclemanagement.service;

import com.paul.vehiclemanagement.Utils.InvalidDataException;
import com.paul.vehiclemanagement.Utils.VehicleDateUtils;
import com.paul.vehiclemanagement.domain.Vehicle;
import com.paul.vehiclemanagement.model.VehicleModel;
import com.paul.vehiclemanagement.model.VehicleTypeModel;
import com.paul.vehiclemanagement.repository.VehicleRepository;
import com.paul.vehiclemanagement.repository.VehicleTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class VehicleManagementService implements IManagementService<VehicleModel> {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Override
    public List<VehicleModel> getAll() {
        List<VehicleModel> result = new ArrayList<>();
        vehicleRepository.findAll().forEach(v -> result.add(new VehicleModel(v)));

        return result;
    }

    @Override
    public Optional<VehicleModel> getById(Long id) {
        if (id == null) {
            return Optional.of(VehicleModel.builder().vehicleTypeModel(new VehicleTypeModel()).build());
        }

        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        return Optional.ofNullable(vehicleOptional.map(VehicleModel::new).orElse(null));
    }

    @Override
    @Transactional
    public Optional<VehicleModel> saveOrUpdate(@Valid VehicleModel vehicleModel) {
        if (vehicleModel == null) {
            throw new InvalidDataException("Data entered is invalid.");
        }
        Vehicle vehicle = vehicleModel.getVehicleId() != null ? vehicleRepository.findById(vehicleModel.getVehicleId())
                .orElseGet(Vehicle::new) : new Vehicle();
        updateEntity(vehicle, vehicleModel);
        try {
            vehicle = vehicleRepository.save(vehicle);
            vehicleModel = new VehicleModel(vehicle);
        } catch (DataIntegrityViolationException e) {
            Throwable t = e.getCause();
            log.warn("Failed to save/update: {}", vehicleModel, e);
            while ((t != null) && !(t instanceof ConstraintViolationException)) {
                t = t.getCause();
            }
            if (t instanceof ConstraintViolationException) {
                throw new InvalidDataException("Vehicle with given  VIN already exists.");
            }
            throw new InvalidDataException("Data could not be saved.");
        }

        return Optional.of(vehicleModel);
    }

    private void updateEntity(Vehicle vehicle, VehicleModel vehicleModel) {
        vehicle.setPlateNumber(vehicleModel.getPlateNumber().toUpperCase());
        vehicle.setVIN(vehicleModel.getVIN().toUpperCase());
        vehicle.setDateOfRegistration(LocalDate.parse(vehicleModel.getDateOfRegistration(), VehicleDateUtils.FORMATTER));
        if (StringUtils.isNotEmpty(vehicleModel.getVehicleTypeModel().getName())) {
            String typeName = vehicleModel.getVehicleTypeModel().getName().toUpperCase();
            vehicle.setVehicleType(vehicleTypeRepository.findByName(typeName)
                    .orElseThrow(() -> new InvalidDataException("Vehicle Type is invalid.")));
        }
    }

    @Override
    public Optional<VehicleModel> delete(Long id) {
        if(id == null){
            throw new InvalidDataException("Entry does not exist");
        }
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        vehicleRepository.deleteById(id);

        return Optional.of(vehicleOptional.map(vehicle -> new VehicleModel())
                .orElseThrow(() -> new InvalidDataException("Entry does not exist")));
    }
}
