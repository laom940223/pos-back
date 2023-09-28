package com.laron.pos.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="operations")
public class OperationEntity {

    @Id
    @GeneratedValue
    private Long id;


    private OperationType type;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private CashRegisterSession session;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private ClientEntity client;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "provider_id")
    private ClientEntity provider;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="authorized_by")
    private UserEntity authorizedBy;




    @OneToMany( targetEntity = ProductDetailEntity.class, fetch = FetchType.EAGER)
    private List<ProductDetailEntity> products;

    private Float cashAmount;

    private Float cardAmount;

    private Float creditAmount;

    private Float total;

    private LocalDateTime createdAt;
}
