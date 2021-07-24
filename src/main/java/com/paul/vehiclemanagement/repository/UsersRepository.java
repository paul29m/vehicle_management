package com.paul.vehiclemanagement.repository;

import com.paul.vehiclemanagement.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<User, Integer> {
    User findUserByName(String name);
}
