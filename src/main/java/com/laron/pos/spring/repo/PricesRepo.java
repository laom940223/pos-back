package com.laron.pos.spring.repo;

import com.laron.pos.spring.entities.ProductPrices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PricesRepo extends JpaRepository<ProductPrices, Long>{


//    @Query(name = "select * from product_prices p where p.product_id = ?1", nativeQuery = true)
    @Query
    public List<ProductPrices> findAllByProductId(Long id);


}
