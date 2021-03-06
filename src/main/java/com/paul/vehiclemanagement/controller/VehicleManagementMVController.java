package com.paul.vehiclemanagement.controller;

import com.paul.vehiclemanagement.model.VehicleModel;
import com.paul.vehiclemanagement.service.IManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/vehicles/")
@Controller
public class VehicleManagementMVController extends AbstractMVController<VehicleModel> {

    @Autowired
    public VehicleManagementMVController(IManagementService<VehicleModel> vehicleTypeService) {
        this.service = vehicleTypeService;
    }
    
    @Override
    String getListView() {
        return "vehicleList";
    }

    @Override
    String getDetailView() {
        return "vehicleDetails";
    }

    @Override
    String getViewAttribute() {
        return "vehicle";
    }
}
