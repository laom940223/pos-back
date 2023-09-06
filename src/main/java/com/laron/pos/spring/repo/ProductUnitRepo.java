package com.laron.pos.spring.repo;

import com.laron.pos.spring.entities.ProductUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductUnitRepo extends JpaRepository<ProductUnitEntity, Long> {

    @Query
    public Optional<ProductUnitEntity> findByName(String name);
}
