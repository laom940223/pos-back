package com.laron.pos.spring.dtos;

import com.laron.pos.spring.entities.CashRegisterSession;
import com.laron.pos.spring.entities.ClientEntity;
import com.laron.pos.spring.entities.OperationType;
import com.laron.pos.spring.entities.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateSaleOperation {



    @NotNull
    private String type;

    @NotNull
    private Long sessionId;

    @NotNull
    private Long  clientId;

    private Float cashAmount;

    private Float cardAmount;

    private Float creditAmount;

    @NotNull
    private Float total;

    @NotNull
    private List<SaleProductDetailRequest> products;


}
