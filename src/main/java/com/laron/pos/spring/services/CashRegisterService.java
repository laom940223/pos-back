package com.laron.pos.spring.services;

import com.laron.pos.spring.dtos.CreateCashRegisterSession;
import com.laron.pos.spring.entities.CashRegisterSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CashRegisterService {


   public CashRegisterSession openSession(CreateCashRegisterSession session);

   public CashRegisterSession closeSession();

   public List<CashRegisterSession> getLastRegisterSession( Long id);



}
