package com.laron.pos.spring.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "product_units")
@Builder
public class ProductUnitEntity {


    @Id
    @GeneratedValue
    private Long id;


    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String plural;

    @Column( nullable = false)
    private String abbreviation;

    @Column( nullable = false)
    private Boolean fractional;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "buyUnit")
    private List<ProductEntity> buyProducts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "saleUnit")
    private List<ProductEntity> saleProducts;


}
