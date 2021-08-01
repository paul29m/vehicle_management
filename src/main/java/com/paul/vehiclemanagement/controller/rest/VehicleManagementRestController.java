package com.paul.vehiclemanagement.controller.rest;

import com.paul.vehiclemanagement.controller.BaseRestController;
import com.paul.vehiclemanagement.model.VehicleModel;
import com.paul.vehiclemanagement.service.IManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/vehicles/")
@RestController
public class VehicleManagementRestController extends BaseRestController<VehicleModel>{

    @Autowired
    public VehicleManagementRestController(IManagementService<VehicleModel> vehicleTypeService) {
        this.service = vehicleTypeService;
    }
}
