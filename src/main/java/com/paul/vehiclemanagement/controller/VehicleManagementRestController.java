package com.paul.vehiclemanagement.controller;

import com.paul.vehiclemanagement.model.VehicleModel;
import com.paul.vehiclemanagement.service.IManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/vehicles/")
@RestController
public class VehicleManagementRestController {

    @Autowired
    protected IManagementService<VehicleModel> vehicleManagementService;

    @GetMapping("/findAll")
    public ResponseEntity<List<VehicleModel>> findAll() {
        return  ResponseEntity.ok(vehicleManagementService.getAll());
    }

    @GetMapping("/findOneById/{id}")
    public ResponseEntity findOne(@PathVariable Long id) {
        Optional<VehicleModel> vehicleModel = vehicleManagementService.getById(id);
        if(vehicleModel.isPresent()){
            return ResponseEntity.ok(vehicleModel);
        }

        return ResponseEntity.notFound().build();
    }

}
