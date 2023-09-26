package com.laron.pos.spring.entities;

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
@Table(name = "clients")
public class ClientEntity {

    @Id
    @GeneratedValue
    private Long id ;


    @Column(nullable = false, unique = true)
    private String name;

    private String address;

    @Column(nullable = true, unique = true)
    private String rfc;

}
