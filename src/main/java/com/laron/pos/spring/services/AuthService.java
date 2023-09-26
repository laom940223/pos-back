package com.laron.pos.spring.services;

import com.laron.pos.spring.dtos.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    public Long getAdminAuthorization(LoginRequest request);
}
