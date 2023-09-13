package com.laron.pos.spring.dtos;


import com.laron.pos.spring.entities.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePriceRequest {



    private Float amount;

    private Float minimum;

    private Integer priceNumber;

    private Long productId;

}
