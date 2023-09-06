package com.laron.pos.spring.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductUnitRequest {



    @NotBlank
    private String name;

    @NotBlank
    private String plural;

    @NotBlank
    private String abbreviation;

    @NotNull
    private Boolean fractional;


}
