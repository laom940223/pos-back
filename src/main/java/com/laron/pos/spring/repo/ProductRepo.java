package com.laron.pos.spring.repo;

import com.laron.pos.spring.entities.ClientEntity;
import com.laron.pos.spring.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Long >{

        @Query
        public Optional<ProductEntity> findByBarcode(String barcode);

        @Query
        public Optional<ProductEntity> findFirstByAltBarcode(String altBarcode);

        @Query
        public Optional<ProductEntity> findByName(String name);

        @Query
        public List<ProductEntity> findByNameContaining(String query);
}
