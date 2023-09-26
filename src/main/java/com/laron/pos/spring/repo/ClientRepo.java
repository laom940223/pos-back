package com.laron.pos.spring.repo;


import com.laron.pos.spring.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepo  extends JpaRepository<ClientEntity, Long> {



    @Query
    public Optional<ClientEntity> findByRfc(String rfc);

    @Query
    public List<ClientEntity> findByNameContaining(String query)
;
}
