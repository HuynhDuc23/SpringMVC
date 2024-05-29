package com.rungroop.service.impl;

import com.rungroop.dto.RegistrationDto;
import com.rungroop.models.Role;
import com.rungroop.models.UserEntity;
import com.rungroop.repository.RoleRepository;
import com.rungroop.repository.UserRepository;
import com.rungroop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository ;
    private RoleRepository roleRepository ;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registrationDto.getUsername());
        userEntity.setPassword(registrationDto.getPassword());
        userEntity.setEmail(registrationDto.getEmail());
        Role role = roleRepository.findByRole("USER");
        // list
        userEntity.setRoles(Arrays.asList(role));
    }
}
