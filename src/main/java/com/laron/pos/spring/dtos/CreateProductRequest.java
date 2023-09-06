package com.laron.pos.spring.dtos;

import com.laron.pos.spring.entities.ProductUnitEntity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateProductRequest {


    @NotBlank
    private String name;


    private String barcode;

    private String description;

    private Long buyUnit;
    private Long saleUnit;

}
