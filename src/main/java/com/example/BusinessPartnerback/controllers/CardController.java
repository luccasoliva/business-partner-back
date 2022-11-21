package com.example.BusinessPartnerback.controllers;

import com.example.BusinessPartnerback.models.Card;
import com.example.BusinessPartnerback.services.CardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CardController {

    private final CardService cardService;


    @GetMapping("/card")
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/card/{cardCode}")
    public Card getCardByCardCode(@PathVariable String cardCode) {
        return cardService.findCardByCardCode(cardCode);
    }

    @PostMapping("/card")
    public Card createNewCard(@RequestBody Card card) {
        return cardService.createNewCard(card);
    }

    @PutMapping("/card/{cardCode}")
    public Card updateCard(@RequestBody Card card, @PathVariable String cardCode) {
        return cardService.updateCard(card, cardCode);
    }

    @DeleteMapping("/card/{cardCode}")
    public void deleteCard(@PathVariable String cardCode) {
        cardService.deleteCard(cardCode);
    }

}
