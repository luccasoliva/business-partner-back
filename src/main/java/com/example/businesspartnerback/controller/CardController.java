package com.example.businesspartnerback.controller;


import com.example.businesspartnerback.dto.CardDto;
import com.example.businesspartnerback.services.CardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RequestMapping("/api/v1/card")
@RestController
@AllArgsConstructor
public class CardController {
    private final CardService cardService;


    @GetMapping
    public ResponseEntity<List<CardDto>> getAllCardsByOwner(Principal principal){
        return ResponseEntity.ok(cardService.findAllCardsByOwner(principal.getName()));
    }

    @PostMapping
    public ResponseEntity<CardDto> createCard(@RequestBody CardDto cardDto, Principal principal){
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{cardCode}")
                .buildAndExpand(cardDto.cardCode())
                .toUri();

        cardService.createCard(cardDto, principal.getName());
        System.out.println(cardDto);
        return ResponseEntity.created(location).body(cardDto);
    }

    //delete method
    @DeleteMapping("/{cardCode}")
    public ResponseEntity<Void> deleteCard(@PathVariable Integer cardCode, Principal principal){
        cardService.deleteCard(cardCode, principal.getName());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ResponseEntity<CardDto> updateCard(CardDto cardDto, Principal principal){
        cardService.updateCard(cardDto, principal.getName());
        return ResponseEntity.ok(cardDto);
    }


}
