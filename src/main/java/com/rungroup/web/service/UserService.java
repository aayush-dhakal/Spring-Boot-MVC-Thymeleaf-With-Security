package com.rungroup.web.service;

import com.rungroup.web.dto.RegistrationDto;
import com.rungroup.web.models.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
}
