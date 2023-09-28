package com.laron.pos.spring.services;


import com.laron.pos.spring.dtos.CreateSaleOperation;
import com.laron.pos.spring.entities.OperationEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OperationService {



    public OperationEntity createSaleOperation(CreateSaleOperation saleOperation);

    public List<OperationEntity> getOperationsBySessionId(Long id);

}
