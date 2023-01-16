package com.example.businesspartnerback.repositories;

import com.example.businesspartnerback.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer> {

    
    Card findByCardCode(Integer cardCode);

    @Query(value="SELECT card.* FROM card WHERE card.owner_id = :ownerId", nativeQuery = true)
    List<Card> findAllCardsByOwnerId(Integer ownerId);

}