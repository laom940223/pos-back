package com.laron.pos.spring.repo;

import com.laron.pos.spring.entities.CashRegisterSession;
import com.laron.pos.spring.entities.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegisterSessionRepo extends JpaRepository<CashRegisterSession, Long> {

    @Query
    List<CashRegisterSession> findAllByRegister(RegisterEntity register);

    @Query(value = "SELECT * FROM REGISTER_SESSION r  where r.register_id = ?1 order by r.created_at DESC limit 2", nativeQuery = true)
    List<CashRegisterSession> getLast2RegisterSession(Long registerId);
}
