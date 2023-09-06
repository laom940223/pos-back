package com.laron.pos.spring.repo;

import com.laron.pos.spring.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

    @Query
    public Optional<UserEntity> findByUsername(String username);


    @Query
    public Optional<UserEntity> findByEmail(String username);
}
