package com.laron.pos.spring.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaleProductDetailRequest {



        private Long productId;

        private Float quantity;

        private Float price;
}
