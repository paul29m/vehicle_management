package com.paul.vehiclemanagement.controller;

import com.paul.vehiclemanagement.Utils.InvalidDataException;
import com.paul.vehiclemanagement.Utils.ModelViewUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
public abstract class AbstractMVController<T> extends BaseRestController<T> {

    @RequestMapping("/findAllModels")
    public ModelAndView findAllModels() {
        log.info("{}: Request to find model all.", this.getClass());
        ModelAndView modelAndView = new ModelAndView(getListView());
        modelAndView.addObject("elements", service.getAll());
        log.info("{}: Successfully found all elements: {}", this.getClass(), modelAndView.getModel());
        return modelAndView;
    }

    @RequestMapping("/findOne/{id}")
    public ModelAndView findOneModel(@PathVariable Long id) {
        log.info("{}: Request to find model by id: {}", this.getClass(), id);
        ModelAndView modelAndView = new ModelAndView(getDetailView());
        Optional<T> vehicleModel = service.getById(id);
        if (vehicleModel.isPresent()) {
            modelAndView.addObject(getViewAttribute(), vehicleModel.get());
            log.info("{}: Successfully found model: {}", this.getClass(), vehicleModel);
        } else {
            return ModelViewUtils.ERROR_PAGE;
        }

        return modelAndView;
    }

    @RequestMapping("/create")
    public ModelAndView createModel() {
        ModelAndView modelAndView = new ModelAndView(getDetailView());
        modelAndView.addObject(getViewAttribute(), service.getById(null).get());
        return modelAndView;
    }

    @RequestMapping(value = "/saveOrUpdateByModel", method = RequestMethod.POST)
    public ModelAndView saveOrUpdateByModel(@ModelAttribute @Valid T UIModel, BindingResult result) {
        log.info("{}: Request to update model: {}", this.getClass(), UIModel);
        ModelAndView modelAndView = new ModelAndView(getDetailView());
        if (result.hasErrors()) {
            modelAndView.addObject(getViewAttribute(), UIModel);
            modelAndView.addObject("errors", result.getFieldErrors());
            return modelAndView;
        }

        try {
            Optional<T> savedModel = service.saveOrUpdate(UIModel);
            if (savedModel.isPresent()) {
                modelAndView.addObject(getViewAttribute(), savedModel.get());
                modelAndView.addObject("success", "Data Saved!");
            } else {
                return ModelViewUtils.ERROR_PAGE;
            }
        } catch (InvalidDataException e) {
            modelAndView.addObject(getViewAttribute(), UIModel);
            modelAndView.addObject("runtimeError", e.getMessage());
        }

        log.info("{}: Updated model information: {}", this.getClass(), modelAndView.getModel());
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ModelAndView deleteOneModel(@PathVariable Long id) {
        log.info("{}: Request to delete by id: {}", this.getClass(), id);
        ModelAndView modelAndView = new ModelAndView(getListView());
        try {
            Optional<T> vehicleModel = service.delete(id);
            modelAndView.addObject("elements", service.getAll());
        } catch (InvalidDataException e) {
            modelAndView.addObject("elements", service.getAll());
            modelAndView.addObject("runtimeError", e.getMessage());
        }
        return modelAndView;
    }

    abstract String getListView();

    abstract String getDetailView();

    abstract String getViewAttribute();
}
