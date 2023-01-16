package com.example.businesspartnerback.repositories;

import com.example.businesspartnerback.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {

     Optional<Owner>findByEmail(String email);
}