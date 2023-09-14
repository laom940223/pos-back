package com.laron.pos.spring.repo;

import com.laron.pos.spring.entities.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepo extends JpaRepository<RegisterEntity, Long> {
}
