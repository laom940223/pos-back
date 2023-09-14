package com.laron.pos.spring.services;

import com.laron.pos.spring.dtos.CreateEditRegister;
import com.laron.pos.spring.entities.RegisterEntity;
import com.laron.pos.spring.exceptions.ResourceNotFound;
import com.laron.pos.spring.repo.RegisterRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RegisterServceImp implements  RegisterService{


    private final RegisterRepo repo;


    @Override
    public RegisterEntity createRegister(CreateEditRegister register) {

        var registerToSave = RegisterEntity.builder()
                .name(register.getName())
                .build();

        return repo.save(registerToSave);

    }

    @Override
    public RegisterEntity getRegisterById(Long id) {

        return repo.findById(id).orElseThrow(()-> new ResourceNotFound("The resorce was not found"));
    }

    @Override
    public String deleteRegister(Long id) {

        var toDelete = repo.findById(id).orElseThrow(()-> new ResourceNotFound("The resorce was not found"));


        repo.delete(toDelete);

        return "Register deleted successfully";
    }

    @Override
    public List<RegisterEntity> getAllRegisters() {
        return repo.findAll();
    }

    @Override
    public RegisterEntity editRegister(Long id, CreateEditRegister register) {
        var toEdit = repo.findById(id).orElseThrow(()-> new ResourceNotFound("The resorce was not found"));

        toEdit.setName(register.getName());
        return repo.save(toEdit);

    }
}
