package com.laron.pos.spring.services;

import com.laron.pos.spring.dtos.ProductUnitRequest;
import com.laron.pos.spring.entities.ProductUnitEntity;
import com.laron.pos.spring.exceptions.FieldErrorException;
import com.laron.pos.spring.exceptions.ResourceNotFound;
import com.laron.pos.spring.repo.ProductUnitRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


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

    @Override
    public String deleteProductUnit(Long id) {

        var unitToDelete = productUnitRepo.findById(id).orElseThrow(()-> new ResourceNotFound("The unit with id :" + id + " was not found"));


        productUnitRepo.delete(unitToDelete);

        return "Unit deleted successfully";

    }

    @Override
    public ProductUnitEntity editProducUnit(Long id, ProductUnitRequest unitRequest) {

        var existingUnit = productUnitRepo.findById(id).orElseThrow(()-> new ResourceNotFound("The unit with id: " + id + " was not found"));


        var update = false;



        if(!unitRequest.getName().isBlank() && !unitRequest.getName().equalsIgnoreCase( existingUnit.getName())){

            existingUnit.setName(unitRequest.getName());
            update= true;
        }


        if(!unitRequest.getPlural().isBlank() && !unitRequest.getPlural().equalsIgnoreCase( existingUnit.getPlural())){

            existingUnit.setPlural(unitRequest.getPlural());
            update= true;
        }


        if(!unitRequest.getAbbreviation().isBlank() && !unitRequest.getAbbreviation().equalsIgnoreCase( existingUnit.getAbbreviation())){

            existingUnit.setAbbreviation(unitRequest.getAbbreviation());
            System.out.println(unitRequest.getAbbreviation());
            update= true;
        }


        if(unitRequest.getFractional() != existingUnit.getFractional()){

            existingUnit.setFractional(unitRequest.getFractional());
            update=true;
        }




        if(update){

            return productUnitRepo.save(existingUnit);
        }


        return existingUnit;




    }
}
