package com.rungroop.service;

import com.rungroop.dto.RegistrationDto;
import com.rungroop.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    public UserEntity findByEmail(String email);

    public UserEntity findByUsername(String username);
}
