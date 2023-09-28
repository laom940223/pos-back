package com.laron.pos.spring.repo;

import com.laron.pos.spring.entities.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepo extends JpaRepository<OperationEntity, Long> {

    @Query
    public List<OperationEntity> findBySessionId(Long sessionId);
}
