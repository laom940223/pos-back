package com.laron.pos.spring.controllers;


import com.laron.pos.spring.dtos.CreateSaleOperation;
import com.laron.pos.spring.dtos.utils.ServerResponse;
import com.laron.pos.spring.services.OperationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="api/operations")
public class OperationController {

    private final OperationService operationService;


    @PostMapping(path = "/sales")
    public ResponseEntity<ServerResponse<?>> creataSaleOperation(@Valid @RequestBody CreateSaleOperation saleOperation){
        return ResponseEntity.ok(
                ServerResponse.builder()
                        .data(operationService.createSaleOperation(saleOperation))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }


}
