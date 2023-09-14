package com.laron.pos.spring.controllers;

import com.laron.pos.spring.dtos.CreateProviderRequest;
import com.laron.pos.spring.dtos.utils.ServerResponse;
import com.laron.pos.spring.services.ProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/providers")
@RequiredArgsConstructor
public class ProviderController {


    private final ProviderService providerService;

    @GetMapping
    public ResponseEntity<ServerResponse<?>> getAllProviders(){
        return ResponseEntity.ok(
                ServerResponse.builder()
                        .data( providerService.getAllProviders() )
                        .build()
        );
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ServerResponse<?>> getProviderById(@PathVariable Long id){

        return ResponseEntity.ok(

                ServerResponse.builder()
                        .data(providerService.getProviderById(id))
                        .build()
        );

    }

    @PostMapping
    public ResponseEntity<ServerResponse<?>> createProvider(@Valid @RequestBody CreateProviderRequest request){

        return ResponseEntity.ok(
                ServerResponse.builder()
                        .data(providerService.createProvider(request))
                        .build()
        );
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ServerResponse<?>> editProvider(@PathVariable Long id, @RequestBody CreateProviderRequest providerRequest){

        return ResponseEntity.ok(
                ServerResponse.builder()
                        .data(providerService.editProvider(id, providerRequest))
                        .build()
        );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ServerResponse<?>> deleteProvider(@PathVariable Long id){

        return ResponseEntity.ok(

                ServerResponse.builder()
                        .data(providerService.deleteProvider(id))
                        .build()
        );

    }




}
