package com.laron.pos.spring.controllers;


import com.laron.pos.spring.dtos.CreateProductRequest;
import com.laron.pos.spring.dtos.ProductUnitRequest;
import com.laron.pos.spring.dtos.utils.ServerResponse;
import com.laron.pos.spring.services.ProductUnitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/product-unit")
public class ProductUnitController {

    private final ProductUnitService productUnitService;



    @PostMapping
    public ResponseEntity<ServerResponse<?>> createProductUnit(@Valid @RequestBody ProductUnitRequest productUnitRequest){

        return ResponseEntity.ok(
                ServerResponse.builder()
                        .data(productUnitService.createProductUnit(productUnitRequest))
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ServerResponse<?>> getAllProductUnits() {

        return ResponseEntity.ok(
                ServerResponse.builder()
                        .data(productUnitService.getAllProductUnits())
                        .build()
        );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ServerResponse<?>> deleteProductUnit (@PathVariable Long id){


        return ResponseEntity.ok(
                ServerResponse.builder()
                        .data(productUnitService.deleteProductUnit(id))
                        .build()
        );
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<ServerResponse<?>> editProduct(@PathVariable Long id, @RequestBody ProductUnitRequest unitRequest){

        return ResponseEntity.ok(
                ServerResponse.builder()
                        .data(productUnitService.editProducUnit(id, unitRequest))
                        .build()

        );
    }


}
