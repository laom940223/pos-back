package com.laron.pos.spring.dtos;

import com.laron.pos.spring.entities.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserRequest {



    @NotBlank(message = "Please provide a username")
    private String username;


    @NotBlank(message = "Please provide a name")
    private String name;

    @NotBlank(message = "Please provide a lastname")
    private String lastname;


    @Email(message = "Please provide a valid email")
    private String email;


    @NotBlank(message = "Please provide a username")
    private String password;

    @NotBlank(message = "Please confirm your password")
    private String confirmPassword;


    @NotBlank( message = "Please provide a valid role")
    private  String role;
}
