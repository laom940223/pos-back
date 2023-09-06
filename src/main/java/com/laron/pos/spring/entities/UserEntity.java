package com.laron.pos.spring.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="users")
public class UserEntity {


    @Id
    @GeneratedValue
    private Long id;



    @Column(unique = true, nullable = false)
    private String username;


    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String email;

    @JsonIgnore
    private String password;

    @Column(nullable = false)
    private UserRole role;
}
