package com.example.businesspartnerback.dto;

import com.example.businesspartnerback.models.Owner;
import lombok.Builder;

/**
 * A DTO for the {@link Owner} entity
 */
@Builder
public record OwnerDto(String name,
                       String email,
                       String password){
}