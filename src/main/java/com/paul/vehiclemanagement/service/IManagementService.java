package com.paul.vehiclemanagement.service;

import java.util.List;
import java.util.Optional;

public interface IManagementService<T> {

    List<T> getAll();

    Optional<T> getById(Long id);

    Optional<T> saveOrUpdate(T vehicleTypeModel);

    Optional<T> delete(Long id);
}
