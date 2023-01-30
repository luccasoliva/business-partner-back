package com.example.businesspartnerback.dto;

import com.example.businesspartnerback.models.Card;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link Card} entity
 */@Builder
public record CardDto(Integer cardCode,
                      String cardName,
                      String avatar,
                      String address,
                      String tel,
                      String email,
                      String description) {
}