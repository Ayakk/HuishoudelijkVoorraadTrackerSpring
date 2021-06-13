package com.example.HuishoudelijkVoorraadTrackerSpring.security.services;


import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Account;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    AccountRepo accountRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = accountRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Er kon geen gebruiker gevonden worden met de volgende username: " + username));

        return UserDetailsImpl.build(user);
    }

}
