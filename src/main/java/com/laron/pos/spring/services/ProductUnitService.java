package com.laron.pos.spring.services;

import com.laron.pos.spring.dtos.ProductUnitRequest;
import com.laron.pos.spring.entities.ProductUnitEntity;

import java.util.List;

public interface ProductUnitService {


    public ProductUnitEntity createProductUnit(ProductUnitRequest productUnitRequest);

    public List<ProductUnitEntity> getAllProductUnits();
}
