package com.laron.pos.spring.controllers;

import com.laron.pos.spring.dtos.CreateUserRequest;
import com.laron.pos.spring.dtos.EditUserRequest;
import com.laron.pos.spring.dtos.utils.ServerResponse;
import com.laron.pos.spring.entities.UserEntity;
import com.laron.pos.spring.services.UserService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    public ResponseEntity<ServerResponse<?>> getAllUsers(){


        return ResponseEntity.ok(

                ServerResponse.builder()
                        .data( userService.getAllUsers() )
                        .build()

        );
    }

    @PostMapping
    public ResponseEntity<ServerResponse<?>> createUser(@Valid @RequestBody CreateUserRequest userRequest){


        return ResponseEntity.ok(

                ServerResponse.builder()
                        .data(userService.createUser(userRequest))
                        .build()
        );

    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ServerResponse<?>> deleteUser (@PathVariable Long id){


        return ResponseEntity.ok(

                ServerResponse.builder()
                        .data(userService.deleteUser(id))
                        .build()
        );
    }



    @PutMapping(path = "/{id}")
    public ResponseEntity<ServerResponse<?>> editUser(@PathVariable Long id, @RequestBody EditUserRequest userRequest){

            return ResponseEntity.ok(

                    ServerResponse.builder()
                            .data(userService.editUser(id, userRequest))
                            .build()

            );

    }


}
