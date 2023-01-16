package com.example.businesspartnerback.security;

import com.example.businesspartnerback.models.Owner;
import com.example.businesspartnerback.repositories.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final OwnerRepository ownerRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Owner owner = ownerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserDetailsImpl(owner);
    }
}
