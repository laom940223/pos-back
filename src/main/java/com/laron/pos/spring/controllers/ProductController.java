package com.laron.pos.spring.controllers;

import com.laron.pos.spring.dtos.CreateProductRequest;
import com.laron.pos.spring.dtos.SearchRequest;
import com.laron.pos.spring.dtos.utils.ServerResponse;
import com.laron.pos.spring.repo.PricesRepo;
import com.laron.pos.spring.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Server;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
//    private final PricesRepo pricesRepo;

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


//        System.out.println(productRequest);

        return ResponseEntity.ok(
          ServerResponse.builder()
                  .data(productService.createProduct(productRequest))
                  .timestamp(LocalDateTime.now())
                  .build()

        );
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ServerResponse<?>> deleteProduct (@PathVariable Long id){

        return  ResponseEntity.ok(
                ServerResponse.builder()
                        .data(productService.deleteProduct(id))
                        .build()
        );

    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<ServerResponse<?>> editProduct(@PathVariable Long id, @RequestBody CreateProductRequest productRequest){


        return ResponseEntity.ok(

                ServerResponse.builder()
                        .data(productService.editProduct(id, productRequest))
                        .build()
        );

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ServerResponse<?>> getProductById(@PathVariable Long id){


        return ResponseEntity.ok(

                ServerResponse.builder()
                        .data(productService.getProductById(id))
                        .build()
        );

    }


    @PostMapping(path = "/barcode")
    public ResponseEntity<ServerResponse<?>> searchByBarcode(@RequestBody SearchRequest searchRequest){


        return ResponseEntity.ok(
                ServerResponse.builder()
                        .data(productService.getProductByBarcode(searchRequest.getQuery()))
                        .build()
        );
    }

    @PostMapping(path = "/search")
    public ResponseEntity<ServerResponse<?>> searchByName(@RequestBody SearchRequest searchRequest){


        return ResponseEntity.ok(
                ServerResponse.builder()
                        .data(productService.searchProductByName(searchRequest.getQuery()))
                        .build()
        );
    }

}
