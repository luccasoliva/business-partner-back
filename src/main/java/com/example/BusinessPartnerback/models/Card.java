package com.example.BusinessPartnerback.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Card {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime createdAt;

    @JsonProperty("CardName")
    private String cardName;

    private String avatar;

    @JsonProperty("Address")
    private String address;

    @JsonProperty("ZipCode")
    private String zipCode;

    @JsonProperty("CardCode")
    private String cardCode;


}
