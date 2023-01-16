package com.example.businesspartnerback.services;

import com.example.businesspartnerback.dto.OwnerDto;
import com.example.businesspartnerback.models.Owner;
import com.example.businesspartnerback.repositories.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OwnerService {
    private final OwnerRepository ownerRepository;

    public void registerOwner(OwnerDto ownerDto){

        ownerRepository.findByEmail(ownerDto.email())
                .ifPresent(owner -> {
                    throw new IllegalStateException("User already exists");
                });

        if(ownerDto.password().isEmpty() || ownerDto.email().isEmpty()){
            throw new IllegalStateException("Password or email is empty");
        }
        Owner owner = Owner.builder()
                .name(ownerDto.name())
                .email(ownerDto.email())
                .password(new BCryptPasswordEncoder().encode(ownerDto.password()))
                .isEnable(true)
                .build();
        ownerRepository.save(owner);
    }



}
