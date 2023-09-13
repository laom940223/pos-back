package com.laron.pos.spring.services;

import com.laron.pos.spring.dtos.CreateProductRequest;
import com.laron.pos.spring.entities.ProductEntity;
import com.laron.pos.spring.entities.ProductPrices;
import com.laron.pos.spring.exceptions.FieldErrorException;
import com.laron.pos.spring.exceptions.ResourceNotFound;
import com.laron.pos.spring.repo.PricesRepo;
import com.laron.pos.spring.repo.ProductRepo;
import com.laron.pos.spring.repo.ProductUnitRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService{


    private final ProductRepo productRepo;
    private final ProductUnitRepo unitRepo;
    private final PricesRepo pricesRepo;

    @Override
    public ProductEntity createProduct(CreateProductRequest productRequest) {

        if (productRepo.findByName(productRequest.getName()).isPresent()) {
            throw new FieldErrorException("General message", "name", "Name is already in use");
        };

        if (productRepo.findByBarcode(productRequest.getBarcode()).isPresent()) {
            throw new FieldErrorException("General message", "barcode", "Barcode is already in use");
        };

        if(  !productRequest.getAltBarcode().isBlank()  &&  productRepo.findFirstByAltBarcode(productRequest.getAltBarcode()).isPresent()) {
            throw new FieldErrorException("General message", "altBarcode", "Barcode is already in use");
        };

        var saleUnit = unitRepo.findById(productRequest.getSaleUnit()).orElseThrow(()->{
            return new FieldErrorException("General message", "saleUnit","Please enter a valid unit");
        });

        var buyUnit = unitRepo.findById(productRequest.getSaleUnit()).orElseThrow(()->{
            return new FieldErrorException("General message", "buyUnit","Please enter a valid unit");
        });

        var altBarcode= productRequest.getAltBarcode().equals("") ? null : productRequest.getAltBarcode();



        for(var i =0; i<productRequest.getPrices().size() ; i++){
            var currentPrice = productRequest.getPrices().get(i);

            switch (currentPrice.getPriceNumber()) {
                case 1 -> System.out.println("Price " + currentPrice);
                case 2 -> {
                    if(currentPrice.getAmount() > 0 && currentPrice.getMinimum() <=1 || currentPrice.getMinimum().isNaN() ){
                        throw  new FieldErrorException("General error", "minimum2", "Needs to be greater than 1");
                    }
                }
                default -> System.out.println("Default");
            }
        }







        var createdProduct = ProductEntity.builder()
                    .name(productRequest.getName())
                    .price(0F)
                    .stock(0F)
                    .barcode(productRequest.getBarcode())
                    .altBarcode(altBarcode)
                    .saleUnit(saleUnit)
                    .buyUnit(buyUnit)

                    .build();

        var savedProduct = productRepo.save(createdProduct);

        var prices = productRequest.getPrices().stream().map(product->{

                return ProductPrices.builder()
                        .minimum(product.getMinimum())
                        .amount(product.getAmount())
                        .priceNumber(product.getPriceNumber())
                        .product(savedProduct)
                        .build();

        }).toList();

        var savedPrices = pricesRepo.saveAll(prices);


        return productRepo.findById(savedProduct.getId()).orElse(savedProduct);


    }

    @Override
    public List<ProductEntity> getAllProducts() {
       return productRepo.findAll();
    }

    @Override
    public String deleteProduct(Long id) {

        var product = productRepo.findById(id).orElseThrow(()-> new ResourceNotFound("The product with id:" + id + " was not found"));


        productRepo.delete(product);


        return "Product deleted successfully";



    }

    @Override
    public ProductEntity editProduct(Long id, CreateProductRequest productRequest) {

        var exist = productRepo.findById(id).orElseThrow(()-> new ResourceNotFound("The product with id:" + id + " was not found"));

        var check =productRepo.findByName(productRequest.getName());

        if (check.isPresent()) {

            if(!check.get().getId().equals(exist.getId())){
                throw new FieldErrorException("General message", "name", "Name is already in use");
            }
        };


        check = productRepo.findByBarcode(productRequest.getBarcode());

        if (check.isPresent()) {
            if(!check.get().getId().equals(exist.getId())){
                throw new FieldErrorException("General message", "barcode", "Barcode is already in use");
            }
        };

        check= productRepo.findFirstByAltBarcode(productRequest.getAltBarcode());

        if(check.isPresent()){

            if( !check.get().getId().equals(exist.getId())  && !productRequest.getAltBarcode().isBlank()   ) {
                throw new FieldErrorException("General message", "altBarcode", "Barcode is already in use");
            };

        }



        var saleUnit = unitRepo.findById(productRequest.getBuyUnit()).orElseThrow(()->{
            return new FieldErrorException("General message", "saleUnit","Please enter a valid unit");
        });

        var buyUnit = unitRepo.findById(productRequest.getSaleUnit()).orElseThrow(()->{
            return new FieldErrorException("General message", "buyUnit","Please enter a valid unit");
        });

        var altBarcode= Objects.nonNull(productRequest.getAltBarcode()) && productRequest.getBarcode().equals("") ? null : productRequest.getAltBarcode();


        for(var i =0; i<productRequest.getPrices().size() ; i++){
            var currentPrice = productRequest.getPrices().get(i);

            switch (currentPrice.getPriceNumber()) {
                case 1 -> System.out.println("Price " + currentPrice);
                case 2 -> {
                    if(currentPrice.getAmount() > 0 && currentPrice.getMinimum() <=1 || currentPrice.getMinimum().isNaN() ){
                        throw  new FieldErrorException("General error", "minimum2", "Needs to be greater than 1");
                    }
                }
                default -> System.out.println("Default");
            }
        }

        var updatedProduct = ProductEntity.builder()
                .id(exist.getId())
                .name(productRequest.getName())
                .price(0F)
                .stock(exist.getStock())
                .barcode(productRequest.getBarcode())
                .altBarcode(altBarcode)
                .saleUnit(saleUnit)
                .buyUnit(buyUnit)
                .build();

        var savedProduct = productRepo.save(updatedProduct);


        var existingPrices = pricesRepo.findAllByProductId(updatedProduct.getId());



         existingPrices.forEach(price ->{

             if(price.getPriceNumber().equals(1)){
                 price.setAmount( productRequest.getPrices().get(0).getAmount());
                 price.setMinimum(productRequest.getPrices().get(0).getMinimum());
             }

             if(price.getPriceNumber().equals(2)){
                 price.setAmount( productRequest.getPrices().get(1).getAmount());
                 price.setMinimum(productRequest.getPrices().get(1).getMinimum());
             }
        } );



         pricesRepo.saveAll(existingPrices);



        return productRepo.findById(savedProduct.getId()).orElse(savedProduct);




    }

    @Override
    public ProductEntity getProductById(Long id) {

        return productRepo.findById(id).orElseThrow(()-> new ResourceNotFound("The product was not found"));

    }
}
