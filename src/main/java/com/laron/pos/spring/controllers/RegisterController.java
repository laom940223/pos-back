package com.laron.pos.spring.controllers;

import com.laron.pos.spring.dtos.CreateEditRegister;
import com.laron.pos.spring.dtos.utils.ServerResponse;
import com.laron.pos.spring.services.RegisterService;
import jakarta.validation.Valid;
import org.apache.catalina.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/register")
public class RegisterController {


    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }


    @GetMapping

    public ResponseEntity<ServerResponse<?>> getAllRegisters(){

        return  ResponseEntity.ok(

                ServerResponse.builder()
                        .data(
                                registerService.getAllRegisters()
                        )
                        .build()

        );
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ServerResponse<?>> getRegisterById(@PathVariable  Long id){


        return ResponseEntity.ok(

                ServerResponse.builder()
                        .data(registerService.getRegisterById(id))
                        .build()

        );

    }


    @PostMapping
    public ResponseEntity<ServerResponse<?>> createRegister(@Valid @RequestBody CreateEditRegister register){
        return ResponseEntity.ok(

                ServerResponse.builder()
                        .data(registerService.createRegister(register))
                        .build()
        );

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ServerResponse<?>> editRegister(@PathVariable Long id, @Valid @RequestBody CreateEditRegister register){
        return ResponseEntity.ok(

                ServerResponse.builder()
                        .data(registerService.editRegister(id, register))
                        .build()
        );

    }
}
