package com.laron.pos.spring.controllers;


import com.laron.pos.spring.dtos.LoginRequest;
import com.laron.pos.spring.dtos.utils.ServerResponse;
import com.laron.pos.spring.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/authorize")
    public ResponseEntity<ServerResponse<?>> authorizeAction(@Valid @RequestBody LoginRequest request){


        var map = new HashMap<String, Long>();

        var id = authService.getAdminAuthorization(request);

        map.put("authorizedBy", id);

        return ResponseEntity.ok(

                ServerResponse.builder()
                        .data(map)
                        .build()
        );




    }
}
