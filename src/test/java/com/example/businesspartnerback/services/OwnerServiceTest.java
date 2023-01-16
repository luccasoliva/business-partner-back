package com.example.businesspartnerback.services;

import com.example.businesspartnerback.dto.OwnerDto;
import com.example.businesspartnerback.models.Owner;
import com.example.businesspartnerback.repositories.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {OwnerService.class})
@RunWith(MockitoJUnitRunner.class)
class OwnerServiceTest {

    @Test
    void testRegisterOwner() {
        OwnerDto ownerDto = new OwnerDto("John", "john@example.com", "password");
        OwnerRepository mockOwnerRepository = mock(OwnerRepository.class);
        when(mockOwnerRepository.findByEmail(ownerDto.email())).thenReturn(Optional.empty());

        OwnerService ownerService = new OwnerService(mockOwnerRepository);
        ownerService.registerOwner(ownerDto);

        verify(mockOwnerRepository, times(1)).save(any(Owner.class));
    }

    @Test
    void testRegisterOwner_userAlreadyExists() {
        OwnerDto ownerDto = new OwnerDto("John", "john@example.com", "password");
        OwnerRepository mockOwnerRepository = mock(OwnerRepository.class);
        when(mockOwnerRepository.findByEmail(ownerDto.email())).thenReturn(Optional.of(new Owner()));

        OwnerService ownerService = new OwnerService(mockOwnerRepository);
        try {
            ownerService.registerOwner(ownerDto);

        } catch (IllegalStateException e) {
            assertEquals("User already exists", e.getMessage());
        }

        verify(mockOwnerRepository, times(0)).save(any(Owner.class));
    }

    @Test
    void testRegisterOwner_passwordOrEmailIsEmpty() {
        OwnerDto ownerDto = new OwnerDto("John", "", "password");
        OwnerRepository mockOwnerRepository = mock(OwnerRepository.class);
        when(mockOwnerRepository.findByEmail(ownerDto.email())).thenReturn(Optional.empty());

        OwnerService ownerService = new OwnerService(mockOwnerRepository);
        try {
            ownerService.registerOwner(ownerDto);

        } catch (IllegalStateException e) {
            assertEquals("Password or email is empty", e.getMessage());
        }

        verify(mockOwnerRepository, times(0)).save(any(Owner.class));
    }





}


