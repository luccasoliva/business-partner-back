package com.example.businesspartnerback.mappers;

import com.example.businesspartnerback.dto.OwnerDto;
import com.example.businesspartnerback.models.Owner;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper {

    //toDto
    public OwnerDto toDto(Owner owner){
        return OwnerDto.builder()
                .name(owner.getName())
                .email(owner.getEmail())
                .password(owner.getPassword())
                .build();
    }
    public Owner toEntity(OwnerDto ownerDto){
        return Owner.builder()
                .name(ownerDto.name())
                .email(ownerDto.email())
                .password(ownerDto.password())
                .build();
    }
}
