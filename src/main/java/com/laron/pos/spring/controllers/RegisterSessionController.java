package com.laron.pos.spring.controllers;

import com.laron.pos.spring.dtos.CreateCashRegisterSession;
import com.laron.pos.spring.dtos.utils.ServerResponse;
import com.laron.pos.spring.entities.CashRegisterSession;
import com.laron.pos.spring.services.CashRegisterService;
import com.laron.pos.spring.services.OperationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/register-sessions")
public class RegisterSessionController {

    private final CashRegisterService registerService;
    private final OperationService operationService;

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



    @GetMapping(path = "/operations/register/{sessionId}")
    public ResponseEntity<ServerResponse<?>> getOperationsBySessionId(@PathVariable Long sessionId){

        return ResponseEntity.ok(

                ServerResponse.builder()
                        .data(operationService.getOperationsBySessionId(sessionId))
                        .timestamp(LocalDateTime.now())
                        .build()


        );

    }
}
