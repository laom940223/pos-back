package com.laron.pos.spring.services;


import com.laron.pos.spring.dtos.CreateClientRequest;
import com.laron.pos.spring.entities.ClientEntity;
import com.laron.pos.spring.exceptions.FieldErrorException;
import com.laron.pos.spring.exceptions.ResourceNotFound;
import com.laron.pos.spring.repo.ClientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClientServiceImp implements  ClientService{


    private final ClientRepo clientRepo;

    @Override
    public List<ClientEntity> getAllClients() {
        return clientRepo.findAll();
    }

    @Override
    public ClientEntity getClientById(Long id) {
        return clientRepo.findById(id).orElseThrow(()-> new ResourceNotFound("The client was not found"));
    }

    @Override
    public ClientEntity createClient(CreateClientRequest request) {

        var address = request.getAddress().isBlank() ? null : request.getAddress();
        var rfc = request.getRfc().isBlank() ? null : request.getRfc();

        if(Objects.nonNull(rfc)){
            var  rfcInUse = clientRepo.findByRfc(rfc);

            if(rfcInUse.isPresent()) throw  new FieldErrorException("General", "rfc","The RFC is already in use");
        }

        var newClient = ClientEntity.builder()
                .name(request.getName())
                .rfc(rfc)
                .address(address)
                .build();


        return clientRepo.save(newClient);
    }

    @Override
    public ClientEntity editClient(Long id, CreateClientRequest request) {

        var existingClient = clientRepo.findById(id).orElseThrow(()-> new FieldErrorException("General", "rfc","The RFC is already in use"));


        var address = request.getAddress().isBlank() ? null : request.getAddress();
        var rfc =  request.getRfc();

        if(Objects.nonNull(rfc)){

            var  rfcInUse = clientRepo.findByRfc(rfc);
            if(rfcInUse.isPresent() && !rfcInUse.get().getId().equals(id)) throw  new FieldErrorException("General", "rfc","The RFC is already in use");
        }

        existingClient.setName(request.getName());
        existingClient.setRfc(rfc);
        existingClient.setAddress(address);

        return clientRepo.save(existingClient);
    }

    @Override
    public String deleteClient(Long id) {
        var existingClient = clientRepo.findById(id).orElseThrow(()-> new FieldErrorException("General", "rfc","The RFC is already in use"));

        clientRepo.delete(existingClient);

        return "Client deleted successfully";
    }

    @Override
    public List<ClientEntity> searchClient(String query) {

        System.out.println(query);
        return clientRepo.findByNameContaining(query);
    }


}
