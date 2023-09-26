package com.laron.pos.spring.controllers;

import com.laron.pos.spring.dtos.CreateCashRegisterSession;
import com.laron.pos.spring.dtos.utils.ServerResponse;
import com.laron.pos.spring.entities.CashRegisterSession;
import com.laron.pos.spring.services.CashRegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/register-sessions")
public class RegisterSessionController {

    private final CashRegisterService registerService;


    @GetMapping(path = "/{id}")
    public ResponseEntity<ServerResponse<?>> getRegisterSession(@PathVariable Long id){
        return ResponseEntity.ok(
                ServerResponse.builder()
                        .data(registerService.getLastRegisterSession(id))
                        .build()
        );
    }


    @PostMapping
    public ResponseEntity<ServerResponse<?>> openNewSession(@Valid @RequestBody CreateCashRegisterSession session){


        return ResponseEntity.ok(

                ServerResponse.builder()
                        .data(registerService.openSession(session))
                        .build()

        );

    }

}
