package com.paul.vehiclemanagement.controller;

import com.paul.vehiclemanagement.model.VehicleModel;
import com.paul.vehiclemanagement.service.IManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/vehicles/")
@Controller
public class VehicleManagementController {

    @Autowired
    private IManagementService<VehicleModel> vehicleManagementService;

    @GetMapping("/findAll")
    public List<VehicleModel> findAll() {
        return vehicleManagementService.getAll();
    }

    @RequestMapping("/findOne")
    public String findOneModel(Model model) {
        model.addAttribute("vehicle", vehicleManagementService.getAll().get(0));
        return "vehicleDetails";
    }


}
