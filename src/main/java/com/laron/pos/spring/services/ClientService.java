package com.laron.pos.spring.services;

import ch.qos.logback.core.net.server.Client;
import com.laron.pos.spring.dtos.CreateClientRequest;
import com.laron.pos.spring.entities.ClientEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {


    public List<ClientEntity> getAllClients();


    public ClientEntity getClientById(Long id);

    public ClientEntity createClient(CreateClientRequest request);

    public  ClientEntity editClient(Long id, CreateClientRequest request);

    public String deleteClient(Long id);


    public List<ClientEntity> searchClient(String query);

}
