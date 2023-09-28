package com.laron.pos.spring.services;


import com.laron.pos.spring.dtos.CreateSaleOperation;
import com.laron.pos.spring.entities.OperationEntity;
import com.laron.pos.spring.entities.OperationType;
import com.laron.pos.spring.entities.ProductDetailEntity;
import com.laron.pos.spring.exceptions.FieldErrorException;
import com.laron.pos.spring.repo.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OperationServiceImp  implements  OperationService{

    private final OperationRepo operationRepo;
    private final RegisterSessionRepo sessionRepo;
    private final ClientRepo clientRepo;
    private final ProductRepo productRepo;
    private final ProductDetailRepo productDetailRepo;



    @Override
    public OperationEntity createSaleOperation(CreateSaleOperation saleOperation) {


        var session = sessionRepo.findById(saleOperation.getSessionId()).orElseThrow(()-> new FieldErrorException("General", "sessionId", "The session you provide does not exist"));
        var client  = clientRepo.findById(saleOperation.getClientId()).orElseThrow(()-> new FieldErrorException("General", "clientId", "The client you provide does not exist"));
        OperationType type ;

        try{
            type = OperationType.valueOf(saleOperation.getType());
        }catch (IllegalArgumentException ex){

            throw  new FieldErrorException("", "type", "Please provide a valid operation type");

        }






         var operation = OperationEntity.builder()
                 .client(client)
                 .type(type)
                 .session(session)
                 .cardAmount(saleOperation.getCardAmount())
                 .cashAmount(saleOperation.getCashAmount())
                 .total(saleOperation.getTotal())
                 .createdAt(LocalDateTime.now())
//                 .products(products)
                 .build();


        operation =  operationRepo.save(operation);


        OperationEntity finalOperation = operation;

        var products = saleOperation.getProducts().stream().map( product -> ProductDetailEntity.builder()
                .operation(finalOperation)
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .product( productRepo.findById(product.getProductId()).orElseThrow( ()-> new FieldErrorException("General", "product", "The product with id does not exist")  ))
                .build()).toList();


        productDetailRepo.saveAll(products);


        return operationRepo.findById(operation.getId()).orElse(operation);


    }

    @Override
    public List<OperationEntity> getOperationsBySessionId(Long id) {
        return operationRepo.findBySessionId(id);
    }
}
