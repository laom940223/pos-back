package com.laron.pos.spring.services;

import com.laron.pos.spring.dtos.CreateUserRequest;
import com.laron.pos.spring.dtos.EditUserRequest;
import com.laron.pos.spring.entities.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public UserEntity createUser(CreateUserRequest userRequest);

    public List<UserEntity> getAllUsers();

    public UserEntity getUserById(Long id);

//    public UserEntity editUser(Long id, )

    public String deleteUser(Long id);


    public UserEntity editUser(Long id, EditUserRequest userRequest);
}
