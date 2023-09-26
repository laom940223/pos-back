package com.laron.pos.spring.services;

import com.laron.pos.spring.dtos.CreateProductRequest;
import com.laron.pos.spring.entities.ProductEntity;
import com.laron.pos.spring.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {


    public ProductEntity createProduct(CreateProductRequest productRequest);

    public List<ProductEntity> getAllProducts();

    public String deleteProduct(Long id);

    public ProductEntity editProduct(Long id, CreateProductRequest productRequest);

    public ProductEntity getProductById( Long id);

    public ProductEntity getProductByBarcode(String barcode);

    public List<ProductEntity> searchProductByName(String query);

}
