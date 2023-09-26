package com.laron.pos.spring.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "register_session")
public class CashRegisterSession {




    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime openAt;
    private Float openingAmount;

    private LocalDateTime closedAt;
    private Float closingAmount;




    @Column(updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "register_id", nullable = false)
    private RegisterEntity register;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "open_authorized_id")
    private UserEntity openAuthorizedBy;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "close_authorized_id")
    private UserEntity closeAuthorizedBy;
}
