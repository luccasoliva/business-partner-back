package com.example.businesspartnerback.mappers;

import com.example.businesspartnerback.dto.CardDto;
import com.example.businesspartnerback.models.Card;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public CardDto toDto(Card card){
        return CardDto.builder()
                .cardCode(card.getCardCode())
                .cardName(card.getCardName())
                .zipCode(card.getZipCode())
                .address(card.getAddress())
                .avatar(card.getAvatar())
                .isPublic(card.getIsPublic())
                .build();
    }

    public Card toEntity(CardDto cardDto){
        return Card.builder()
                .cardName(cardDto.cardName())
                .zipCode(cardDto.zipCode())
                .address(cardDto.address())
                .avatar(cardDto.avatar())
                .isPublic(cardDto.isPublic())
                .build();
    }
}
