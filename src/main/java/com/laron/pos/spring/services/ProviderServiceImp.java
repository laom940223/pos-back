package com.laron.pos.spring.services;

import com.laron.pos.spring.dtos.CreateProviderRequest;
import com.laron.pos.spring.entities.ProviderEntity;
import com.laron.pos.spring.exceptions.FieldErrorException;
import com.laron.pos.spring.exceptions.ResourceNotFound;
import com.laron.pos.spring.repo.ProviderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProviderServiceImp implements ProviderService{


    private final ProviderRepo providerRepo;

    @Override
    public List<ProviderEntity> getAllProviders() {
        return providerRepo.findAll();
    }

    @Override
    public ProviderEntity getProviderById(Long id) {
        return providerRepo.findById(id).orElseThrow(()-> new ResourceNotFound("The provider was not found"));
    }

    @Override
    public ProviderEntity createProvider(CreateProviderRequest provider) {


        Optional<ProviderEntity> alreadyExist ;
        String rfc= null;

        if(Objects.nonNull(provider.getRfc()) && !provider.getRfc().isBlank()){
             alreadyExist = providerRepo.findByRfc(provider.getRfc());
             if(alreadyExist.isPresent()){
                 throw  new FieldErrorException("General", "rfc", "The RFC is already in use");
             }
             rfc = provider.getRfc();
        }



        var newProvider = ProviderEntity.builder()
                .name(provider.getName())
                .rfc(rfc)
                .address(provider.getAddress())
                .build();


        return providerRepo.save(newProvider);


    }

    @Override
    public ProviderEntity editProvider(Long id, CreateProviderRequest provider) {

        var existingProvider = providerRepo.findById(id).orElseThrow(()-> new ResourceNotFound("The provider you are looking for was not found"));

        String rfc= null;

        if(Objects.nonNull(provider.getRfc())){

            var alreadyExist = providerRepo.findByRfc(provider.getRfc());

            if(alreadyExist.isPresent()){
                throw  new FieldErrorException("General", "rfc", "The RFC is already in use");
            }
            rfc = provider.getRfc();
        }


        if(Objects.nonNull(provider.getAddress())){

            existingProvider.setAddress(provider.getAddress());
        }


        existingProvider.setName(provider.getName());
        existingProvider.setRfc(rfc);


        return providerRepo.save(existingProvider);


    }

    @Override
    public String deleteProvider(Long id) {
        var existingProvider = providerRepo.findById(id).orElseThrow(()-> new ResourceNotFound("The provider you are looking for was not found"));

        providerRepo.deleteById(id);

        return "Provider deleted successfully";

    }
}
