package com.laron.pos.spring.controllers;

import com.laron.pos.spring.dtos.CreateProductRequest;
import com.laron.pos.spring.dtos.utils.ServerResponse;
import com.laron.pos.spring.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Server;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ServerResponse<?>>  getAllProducts(){


        return ResponseEntity.ok(

                ServerResponse.builder()
                        .data(productService.getAllProducts())
                        .build()

        )  ;
    }


    @PostMapping
    public ResponseEntity<ServerResponse<?>> createProduct (@Valid @RequestBody CreateProductRequest productRequest){

        return ResponseEntity.ok(

          ServerResponse.builder()
                  .data(productService.createProduct(productRequest))
                  .build()

        );
    }


}
