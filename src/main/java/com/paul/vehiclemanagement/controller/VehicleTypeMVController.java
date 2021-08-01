package com.paul.vehiclemanagement.controller;

import com.paul.vehiclemanagement.model.VehicleTypeModel;
import com.paul.vehiclemanagement.service.IManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/vehicleTypes")
@Controller
public class VehicleTypeMVController extends AbstractMVController<VehicleTypeModel> {

    @Autowired
    public VehicleTypeMVController(IManagementService<VehicleTypeModel> vehicleTypeService) {
        this.service = vehicleTypeService;
    }

    @Override
    String getListView() {
        return "vehicleTypeList";
    }

    @Override
    String getDetailView() {
        return "vehicleTypeDetails";
    }

    @Override
    String getViewAttribute() {
        return "vehicleType";
    }

}
