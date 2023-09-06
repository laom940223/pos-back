package com.laron.pos.spring.services;

import com.laron.pos.spring.dtos.CreateUserRequest;
import com.laron.pos.spring.dtos.EditUserRequest;
import com.laron.pos.spring.entities.UserEntity;
import com.laron.pos.spring.entities.UserRole;
import com.laron.pos.spring.exceptions.FieldErrorException;
import com.laron.pos.spring.exceptions.ResourceNotFound;
import com.laron.pos.spring.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{


    private final UserRepo userRepo;

    @Override
    public UserEntity createUser(CreateUserRequest userRequest) {

        if( !userRequest.getPassword().equalsIgnoreCase(userRequest.getConfirmPassword()) ){
            throw new FieldErrorException("General Error", "confirmPassword", "Passwords must match");
        }


        if(userRepo.findByUsername(userRequest.getUsername()).isPresent() ){
            throw new FieldErrorException("General Error", "username", "Username is already taken");
        }

        if(userRepo.findByEmail(userRequest.getEmail()).isPresent() ){
            throw new FieldErrorException("General Error", "email", "Email is already taken");
        }

        var userRole = UserRole.EMPLOYEE;

        try{

            userRole = UserRole.valueOf(userRequest.getRole());

        }catch (IllegalArgumentException ex){
            userRole = UserRole.EMPLOYEE;
        }



        var userToCreate = UserEntity.builder()
                .name(userRequest.getName())
                .username(userRequest.getUsername())
                .lastname(userRequest.getLastname())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .role(userRole)
                .build();


        return userRepo.save(userToCreate);

    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(()-> new ResourceNotFound("The user with id:" +id + " was not found" ) );
    }

    @Override
    public String deleteUser(Long id) {


        var user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFound("The user with id:" +id + " was not found" ) );

        System.out.println(id);
        userRepo.deleteById(id);

        return "User deleted successfully";
    }

    @Override
    public UserEntity editUser(Long id, EditUserRequest userRequest) {

        var user =  userRepo.findById(id).orElseThrow(()-> new ResourceNotFound("The user with id: " + id + " was not found"));




        return user;

    }
}
