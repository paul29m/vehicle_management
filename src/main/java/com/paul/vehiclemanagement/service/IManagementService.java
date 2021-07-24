package com.paul.vehiclemanagement.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IManagementService<T> {

    ResponseEntity create(T vehicleTypeModel);

    List<T> getAll();

    T getById(Long id);

    ResponseEntity update(T vehicleTypeModel);

    ResponseEntity delete(Long id);
}
