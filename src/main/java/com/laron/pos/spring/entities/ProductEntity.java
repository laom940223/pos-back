package com.laron.pos.spring.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false, unique = true)
    private String name;

    private Float price;

    private Float stock;

    @Column(nullable = false, unique = true)
    private String barcode;

    @Column(nullable = true, unique = true)
    private String altBarcode;

    @OneToMany(targetEntity = ProductPrices.class, mappedBy = "product" ,fetch = FetchType.EAGER)
    private List<ProductPrices> prices = new ArrayList<>() ;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buy_unit")
    private ProductUnitEntity buyUnit;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sale_unit")
    private ProductUnitEntity saleUnit;
}
