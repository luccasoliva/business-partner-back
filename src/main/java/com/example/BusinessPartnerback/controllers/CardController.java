package com.example.BusinessPartnerback.controllers;

import com.example.BusinessPartnerback.models.Card;
import com.example.BusinessPartnerback.services.CardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/card")
@AllArgsConstructor
@CrossOrigin(origins = "https://business-partner-front.vercel.app/")
public class CardController {

    private final CardService cardService;


    @GetMapping
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/{cardCode}")
    public Card getCardByCardCode(@PathVariable String cardCode) {
        return cardService.findCardByCardCode(cardCode);
    }

    @PostMapping
    public Card createNewCard(@RequestBody Card card) {
        return cardService.createNewCard(card);
    }

    @PutMapping("/{cardCode}")
    public Card updateCard(@RequestBody Card card, @PathVariable String cardCode) {
        return cardService.updateCard(card, cardCode);
    }

    @DeleteMapping("/{cardCode}")
    public void deleteCard(@PathVariable String cardCode) {
        cardService.deleteCard(cardCode);
    }

}
