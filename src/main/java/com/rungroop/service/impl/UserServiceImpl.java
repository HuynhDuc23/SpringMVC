package com.rungroop.service.impl;

import com.rungroop.dto.RegistrationDto;
import com.rungroop.models.Role;
import com.rungroop.models.UserEntity;
import com.rungroop.repository.RoleRepository;
import com.rungroop.repository.UserRepository;
import com.rungroop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository ;
    private RoleRepository roleRepository ;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository , PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registrationDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        userEntity.setEmail(registrationDto.getEmail());
        Role role = roleRepository.findByName("USER");
        // list
        userEntity.setRoles(Arrays.asList(role));
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
