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
@Table(name = "product_detail")
public class ProductDetailEntity {


    @Id
    @GeneratedValue
    private Long id;

    private Float quantity;

    private Float price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operation_id",nullable = false)
    private OperationEntity operation;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private ProductEntity product;

    private Float factor;
    private Float buyPrice1;
    private Float amountPrice1;
    private Float buyPrice2;
    private Float amountPrice2;

}
