package com.paul.vehiclemanagement.controller;

import com.paul.vehiclemanagement.service.IManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BaseRestController<T>{

    protected IManagementService<T> service;

    @GetMapping("/findAll")
    public ResponseEntity<List<T>> findAll() {
        return  ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/findOneById/{id}")
    public ResponseEntity findOne(@PathVariable Long id) {
        Optional<T> vehicleModel = service.getById(id);
        if(vehicleModel.isPresent()){
            return ResponseEntity.ok(vehicleModel);
        }

        return ResponseEntity.notFound().build();
    }

}
