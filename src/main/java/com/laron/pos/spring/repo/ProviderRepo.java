package com.laron.pos.spring.repo;

import com.laron.pos.spring.entities.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProviderRepo extends JpaRepository<ProviderEntity,Long> {

    @Query
    public Optional<ProviderEntity> findByRfc(String rfc);
}
