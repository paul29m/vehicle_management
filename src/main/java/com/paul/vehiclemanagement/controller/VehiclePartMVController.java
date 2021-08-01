package com.paul.vehiclemanagement.controller;

import com.paul.vehiclemanagement.model.VehiclePartModel;
import com.paul.vehiclemanagement.service.IManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/vehicleParts")
@Controller
public class VehiclePartMVController extends AbstractMVController<VehiclePartModel> {

    @Autowired
    public VehiclePartMVController(IManagementService<VehiclePartModel> managementService) {
        this.service = managementService;
    }

    @Override
    String getListView() {
        return "vehiclePartsList";
    }

    @Override
    String getDetailView() {
        return "vehiclePartDetails";
    }

    @Override
    String getViewAttribute() {
        return "vehiclePart";
    }

}
