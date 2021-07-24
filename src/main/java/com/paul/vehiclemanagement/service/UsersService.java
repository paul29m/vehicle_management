package com.paul.vehiclemanagement.service;

import com.paul.vehiclemanagement.domain.User;
import com.paul.vehiclemanagement.repository.UsersRepository;
import com.paul.vehiclemanagement.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws AuthenticationException {
        User user = usersRepository.findUserByName(name);
        return new UserPrincipal(user);
    }

}
