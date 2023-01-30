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
                .avatar(card.getAvatar())
                .address(card.getAddress())
                .tel(card.getTel())
                .email(card.getEmail())
                .description(card.getDescription())
                .build();
    }

    public Card toEntity(CardDto cardDto){
        return Card.builder()
                .cardName(cardDto.cardName())
                .avatar(cardDto.avatar())
                .address(cardDto.address())
                .tel(cardDto.tel())
                .email(cardDto.email())
                .description(cardDto.description())
                .build();
    }
}
