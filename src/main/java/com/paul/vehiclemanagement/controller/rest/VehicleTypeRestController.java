package com.paul.vehiclemanagement.controller.rest;

import com.paul.vehiclemanagement.controller.BaseRestController;
import com.paul.vehiclemanagement.model.VehicleTypeModel;
import com.paul.vehiclemanagement.service.IManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/vehicleTypes/")
@RestController
public class VehicleTypeRestController extends BaseRestController<VehicleTypeModel> {

    @Autowired
    public VehicleTypeRestController(IManagementService<VehicleTypeModel> vehicleTypeService) {
        this.service = vehicleTypeService;
    }
}
