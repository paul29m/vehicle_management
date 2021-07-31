package com.paul.vehiclemanagement.service;

import com.paul.vehiclemanagement.Utils.VehicleDateUtils;
import com.paul.vehiclemanagement.domain.Vehicle;
import com.paul.vehiclemanagement.model.VehicleModel;
import com.paul.vehiclemanagement.model.VehicleTypeModel;
import com.paul.vehiclemanagement.repository.VehicleRepository;
import com.paul.vehiclemanagement.repository.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
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
        if(id == null){
            return Optional.of(VehicleModel.builder().vehicleTypeModel(new VehicleTypeModel()).build());
        }

        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        return Optional.ofNullable(vehicleOptional.map(VehicleModel::new).orElse(null));
    }

    @Override
    @Transactional
    public Optional<VehicleModel> saveOrUpdate(@Valid VehicleModel vehicleModel) {
        if(vehicleModel == null){
            throw new RuntimeException();
        }
        Vehicle vehicle =  vehicleModel.getVehicleId() != null ? vehicleRepository.findById(vehicleModel.getVehicleId()).orElseGet(Vehicle::new) : new Vehicle();
        updateEntity(vehicle, vehicleModel);
        vehicle.getVehicleType().setVehicleTypeId(1L);
        vehicleRepository.save(vehicle);

        return Optional.of(vehicleModel);
    }

    private void updateEntity(Vehicle vehicle, VehicleModel vehicleModel) {
        vehicle.setPlateNumber(vehicleModel.getPlateNumber());
        vehicle.setVIN(vehicleModel.getVIN());
        vehicle.setDateOfRegistration(LocalDate.parse(vehicleModel.getDateOfRegistration(), VehicleDateUtils.FORMATTER));
        if (vehicleModel.getVehicleTypeModel().getVehicleTypeId() != null) {
            vehicle.setVehicleType(vehicleTypeRepository.findById(vehicleModel.getVehicleTypeModel().getVehicleTypeId()).orElse(null));
        }
        vehicle.setVehicleType(vehicleTypeRepository.findById(1L).orElse(null));
    }

    @Override
    public Optional<VehicleModel> delete(Long id) {
        return null;
    }
}
