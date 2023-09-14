package com.laron.pos.spring.services;

import com.laron.pos.spring.dtos.CreateEditRegister;
import com.laron.pos.spring.entities.RegisterEntity;

import java.util.List;

public interface RegisterService {

    public RegisterEntity createRegister(CreateEditRegister register);


    public RegisterEntity getRegisterById(Long id);
    public String deleteRegister(Long id);

    public List<RegisterEntity> getAllRegisters();

    public RegisterEntity editRegister(Long id, CreateEditRegister register);

}
