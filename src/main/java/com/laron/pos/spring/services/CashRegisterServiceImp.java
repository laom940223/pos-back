package com.laron.pos.spring.services;

import com.laron.pos.spring.dtos.CreateCashRegisterSession;
import com.laron.pos.spring.entities.CashRegisterSession;
import com.laron.pos.spring.entities.RegisterEntity;
import com.laron.pos.spring.entities.UserEntity;
import com.laron.pos.spring.entities.UserRole;
import com.laron.pos.spring.exceptions.FieldErrorException;
import com.laron.pos.spring.repo.RegisterRepo;
import com.laron.pos.spring.repo.RegisterSessionRepo;
import com.laron.pos.spring.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CashRegisterServiceImp implements  CashRegisterService{


    private final RegisterRepo registerRepo;
    private final RegisterSessionRepo sessionRepo;
    private final UserRepo userRepo;


    @Override
    public CashRegisterSession openSession(CreateCashRegisterSession session) {

        var register = registerRepo.findById(session.getRegisterId()).orElseThrow(()-> new FieldErrorException("General", "register", "The register you provide does not exist"));
        var user = userRepo.findById(session.getUserId()).orElseThrow(()-> new FieldErrorException("General", "user", "The user does not exist"));
        var authorizedBy = userRepo.findById(session.getOpenAuthorizedBy()).orElseThrow(()-> new FieldErrorException("General", "openAuthorized", "The authorization is incorrect"));

        if(authorizedBy.getRole().equals(UserRole.EMPLOYEE)){
            throw  new FieldErrorException("General", "openAuthorizedBy", "An admin or supervisor needs to authorize this action");
        }

        var newSession = CashRegisterSession.builder()
                .openAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .register(register)
                .user(user)
                .openingAmount(session.getOpeningAmount())
                .openAuthorizedBy(authorizedBy)
                .build();


        return sessionRepo.save(newSession);


    }

    @Override
    public CashRegisterSession closeSession() {
        return null;
    }

    @Override
    public List<CashRegisterSession> getLastRegisterSession(Long id) {

        var register = registerRepo.findById(id).orElseThrow(()-> new FieldErrorException("General", "register", "The register you provide does not exist"));

        return sessionRepo.getLast2RegisterSession(id);

    }
}
