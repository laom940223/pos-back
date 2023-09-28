package com.laron.pos.spring.repo;

import com.laron.pos.spring.entities.ProductDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepo extends JpaRepository<ProductDetailEntity, Long> {
}
