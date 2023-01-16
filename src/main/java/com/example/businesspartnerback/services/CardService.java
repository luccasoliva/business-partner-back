package com.example.businesspartnerback.services;

import com.example.businesspartnerback.dto.CardDto;
import com.example.businesspartnerback.mappers.CardMapper;
import com.example.businesspartnerback.models.Card;
import com.example.businesspartnerback.models.Owner;
import com.example.businesspartnerback.repositories.CardRepository;
import com.example.businesspartnerback.repositories.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
public class CardService {
    private CardRepository cardRepository;
    private CardMapper cardMapper;
    private final OwnerRepository ownerRepository;

    public List<CardDto> listAllPublicCards() {

        return cardRepository.findAll()
                .stream()
                .filter(Card::getIsPublic)
                .map(cardMapper::toDto)
                .limit(10)
                .toList();
    }

    public List<CardDto> findAllCardsByOwnerId(String email) {

        return cardRepository
                .findAllCardsByOwnerId(ownerRepository
                        .findByEmail(email).orElseThrow().getOwnerId())
                .stream()
                .map(cardMapper::toDto)
                .toList();
    }

    public void createCard(CardDto cardDto, String email) {
        Owner owner = ownerRepository.findByEmail(email).orElseThrow();
        Card card = cardMapper.toEntity(cardDto);
        card.setOwner(owner);
        card.setCreatedAt(LocalDateTime.now());
        cardRepository.save(card);
    }
    //delete method
    public void deleteCard(Integer cardCode, String email) {
        Owner owner = ownerRepository.findByEmail(email)
                .stream()
                .findFirst()
                .orElseThrow();

        Card card = cardRepository.findByCardCode(cardCode);

        if (Objects.equals(card.getOwner().getOwnerId(), owner.getOwnerId())) {
            cardRepository.delete(card);
        } else {
            throw new IllegalStateException("You can't delete this card");
        }
    }
}