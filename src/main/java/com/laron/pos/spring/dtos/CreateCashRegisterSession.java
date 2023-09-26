package com.laron.pos.spring.dtos;

import com.laron.pos.spring.entities.RegisterEntity;
import com.laron.pos.spring.entities.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCashRegisterSession {

    @NotNull
    private Float openingAmount;

    @NotNull
    private Long registerId;

    @NotNull
    private Long userId;

    @NotNull
    private Long openAuthorizedBy;

}
