package com.laron.pos.spring.dtos;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateProviderRequest {




    @NotBlank
    private String name;


    private String rfc;

    @NotBlank
    private String address;

}
