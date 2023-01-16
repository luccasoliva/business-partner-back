package com.example.businesspartnerback.controller;

import com.example.businesspartnerback.dto.OwnerDto;
import com.example.businesspartnerback.services.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/owner")
@AllArgsConstructor
public class OwnerController {

        private OwnerService ownerService;

    @PostMapping("/register")
    public ResponseEntity<OwnerDto> registerOwner(@RequestBody OwnerDto ownerDto){
        ownerService.registerOwner(ownerDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{cardCode}")
                .buildAndExpand(ownerDto.email())
                .toUri();

        return ResponseEntity.created(location).body(ownerDto);
    }



}
