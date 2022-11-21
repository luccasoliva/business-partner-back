package com.example.BusinessPartnerback.services;

import com.example.BusinessPartnerback.models.Card;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CardService {
    private final String URIA = "https://637283b2348e947299f77e08.mockapi.io/b1s/v2/BusinessPartners";
    RestTemplate restTemplate = new RestTemplate();

    public List<Card> getAllCards() {


        ResponseEntity<List<Card>> rateResponse =
                restTemplate
                        .exchange(URIA,
                                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                                });
        List<Card> cards = rateResponse.getBody();
        assert cards != null;
        return cards;
    }

    public Card findCardByCardCode(String cardCode) {
        ResponseEntity<Card> rateResponse =
                restTemplate
                        .exchange(URIA + "/" + cardCode,
                                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                                });
        Card card = rateResponse.getBody();
        assert card != null;
        return card;
    }

    public Card createNewCard(Card card) {
        card.setCreatedAt(LocalDateTime.now());
        card.setCardCode(String.valueOf(getAllCards().size() + 1));

        restTemplate.postForObject(URIA, card, Card.class);
        return card;
    }

    public Card updateCard(Card card, String cardCode) {
        Card card1 = findCardByCardCode(cardCode);
        card1.setCardName(card.getCardName());
        restTemplate.put(URIA + "/" + cardCode, card1);
        return card1;
    }

    public void deleteCard(String cardCode){
        restTemplate.delete(URIA + "/" + cardCode);
    }


}