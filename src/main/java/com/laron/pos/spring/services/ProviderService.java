package com.laron.pos.spring.services;

import com.laron.pos.spring.dtos.CreateProviderRequest;
import com.laron.pos.spring.entities.ProviderEntity;

import java.util.List;

public interface ProviderService {

    public List<ProviderEntity> getAllProviders();

    public ProviderEntity getProviderById(Long id);


    public ProviderEntity createProvider(CreateProviderRequest provider);

    public ProviderEntity editProvider(Long id, CreateProviderRequest provider);

    public String deleteProvider(Long id);


}
