package com.example.businesspartnerback.dto;

import lombok.Builder;

@Builder
public record RegisterDto(String name,
                          String email,
                          String password) {
}
