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
@Table(name = "products")
public class ProductEntity {


    @Id
    @GeneratedValue
    private  Long id;


    private String name;

    private Float price;

    private Float stock;

    @Column(nullable = true, unique = true)
    private String barcode;

    private String description;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buy_unit")
    private ProductUnitEntity buyUnit;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sale_unit")
    private ProductUnitEntity saleUnit;
}
