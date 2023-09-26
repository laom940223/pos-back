package com.laron.pos.spring.controllers;

import com.laron.pos.spring.dtos.CreateClientRequest;
import com.laron.pos.spring.dtos.CreateProviderRequest;
import com.laron.pos.spring.dtos.utils.ServerResponse;
import com.laron.pos.spring.services.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/clients")
public class ClientsController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<ServerResponse<?>> getAllProviders(){
        return ResponseEntity.ok(
                ServerResponse.builder()
                        .data( clientService.getAllClients() )

                        .build()
        );
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ServerResponse<?>> getProviderById(@PathVariable Long id){

        return ResponseEntity.ok(

                ServerResponse.builder()
                        .data(clientService.getClientById(id))
                        .build()
        );

    }

    @PostMapping
    public ResponseEntity<ServerResponse<?>> createProvider(@Valid @RequestBody CreateClientRequest request){

        return ResponseEntity.ok(
                ServerResponse.builder()
                        .data(clientService.createClient(request))
                        .build()
        );
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ServerResponse<?>> editProvider(@PathVariable Long id, @RequestBody CreateClientRequest clientRequest){

        return ResponseEntity.ok(
                ServerResponse.builder()
                        .data(clientService.editClient(id, clientRequest))
                        .build()
        );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ServerResponse<?>> deleteProvider(@PathVariable Long id){

        return ResponseEntity.ok(

                ServerResponse.builder()
                        .data(clientService.deleteClient(id))
                        .build()
        );

    }

    @PostMapping(path = "/search")
    public ResponseEntity<ServerResponse<?>> search(@RequestParam(name = "query") String query){

        return ResponseEntity.ok(
          ServerResponse.builder()
                  .data(clientService.searchClient(query))
                  .build()
        );
    }

}
