package com.laron.pos.spring.services;

import com.laron.pos.spring.dtos.CreateProductRequest;
import com.laron.pos.spring.entities.ProductEntity;
import com.laron.pos.spring.exceptions.FieldErrorException;
import com.laron.pos.spring.repo.ProductRepo;
import com.laron.pos.spring.repo.ProductUnitRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService{


    private final ProductRepo productRepo;
    private final ProductUnitRepo unitRepo;

    @Override
    public ProductEntity createProduct(CreateProductRequest productRequest) {

        var saleUnit = unitRepo.findById(productRequest.getSaleUnit()).orElseThrow(()->{

            return new FieldErrorException("General message", "saleUnit","Please enter a valid unit");
        });

        var buyUnit = unitRepo.findById(productRequest.getSaleUnit()).orElseThrow(()->{

            return new FieldErrorException("General message", "buyUnit","Please enter a valid unit");
        });


            var barcode = productRequest.getBarcode().equals("") ? null : productRequest.getBarcode();

            var createdProduct = ProductEntity.builder()
                    .name(productRequest.getName())
                    .price(0F)
                    .stock(0F)
                    .barcode(barcode)
                    .saleUnit(saleUnit)
                    .buyUnit(buyUnit)
                    .description(productRequest.getDescription())
                    .build();

            return productRepo.save(createdProduct);


    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepo.findAll();
    }
}
