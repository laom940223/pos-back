package com.laron.pos.spring.services;

import com.laron.pos.spring.dtos.LoginRequest;
import com.laron.pos.spring.entities.UserRole;
import com.laron.pos.spring.exceptions.FieldErrorException;
import com.laron.pos.spring.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements  AuthService{

    private final UserRepo userRepo;

    @Override
    public Long getAdminAuthorization(LoginRequest request) {


        var user = userRepo.findByUsername(request.getUsername()).orElseThrow(()->new FieldErrorException("General", "username", "The username does not exist"));

        if(user.getRole().equals(UserRole.EMPLOYEE)) throw  new FieldErrorException("General", "username", "This user is not authorized to do this");

        if(!user.getPassword().equals(request.getPassword())) throw new FieldErrorException("General", "password", "The password is incorrect");

        return user.getId();


    }
}
