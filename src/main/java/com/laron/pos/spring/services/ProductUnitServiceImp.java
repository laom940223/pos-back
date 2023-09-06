package com.laron.pos.spring.services;

import com.laron.pos.spring.dtos.ProductUnitRequest;
import com.laron.pos.spring.entities.ProductUnitEntity;
import com.laron.pos.spring.exceptions.FieldErrorException;
import com.laron.pos.spring.repo.ProductUnitRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductUnitServiceImp implements  ProductUnitService{


    private final ProductUnitRepo productUnitRepo;

    @Override
    public ProductUnitEntity createProductUnit(ProductUnitRequest productUnitRequest) {

            if(productUnitRepo.findByName(productUnitRequest.getName()).isPresent()){

                    throw new FieldErrorException("General Error", "name", "Name is already in use");

            }



            var newUnit = ProductUnitEntity.builder()
                    .name(productUnitRequest.getName())
                    .plural(productUnitRequest.getPlural())
                    .abbreviation(productUnitRequest.getAbbreviation())
                    .fractional(productUnitRequest.getFractional())
                    .build();


            return productUnitRepo.save(newUnit);



    }

    @Override
    public List<ProductUnitEntity> getAllProductUnits() {
        return productUnitRepo.findAll();
    }
}
