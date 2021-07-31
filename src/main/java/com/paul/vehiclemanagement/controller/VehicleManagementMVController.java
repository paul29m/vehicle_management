package com.paul.vehiclemanagement.controller;

import com.paul.vehiclemanagement.Utils.ModelViewUtils;
import com.paul.vehiclemanagement.model.VehicleModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("/vehicles/")
@Controller
public class VehicleManagementMVController extends VehicleManagementRestController {

    @RequestMapping("/findAllModels")
    public ModelAndView findAllModels() {
        ModelAndView modelAndView = new ModelAndView("vehicleList");
        modelAndView.addObject("vehicles", vehicleManagementService.getAll());
        return modelAndView;
    }

    @RequestMapping("/findOne/{id}")
    public ModelAndView findOneModel(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("vehicleDetails");
        Optional<VehicleModel> vehicleModel = vehicleManagementService.getById(id);
        if (vehicleModel.isPresent()) {
            modelAndView.addObject("vehicle", vehicleModel.get());
        } else {
            return ModelViewUtils.ERROR_PAGE;
        }

        return modelAndView;
    }

    @RequestMapping("/create")
    public ModelAndView createModel() {
        ModelAndView modelAndView = new ModelAndView("vehicleDetails");
        modelAndView.addObject("vehicle", vehicleManagementService.getById(null).get());
        return modelAndView;
    }

    @RequestMapping(value = "/saveOrUpdateByModel", method = RequestMethod.POST)
    public ModelAndView saveOrUpdateByModel(@ModelAttribute @Valid VehicleModel vehicleUIModel, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("vehicleDetails");
        if (result.hasErrors()) {
            modelAndView.addObject("vehicle", vehicleUIModel);
            return modelAndView;
        }

        Optional<VehicleModel> vehicleModel = vehicleManagementService.saveOrUpdate(vehicleUIModel);
        if (vehicleModel.isPresent()) {
            modelAndView.addObject("vehicle", vehicleModel.get());
        } else {
            return ModelViewUtils.ERROR_PAGE;
        }
        return modelAndView;
    }
}
