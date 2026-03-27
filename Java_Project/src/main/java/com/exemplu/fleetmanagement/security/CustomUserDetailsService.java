package com.exemplu.fleetmanagement.security;

import com.exemplu.fleetmanagement.model.AppUser;
import com.exemplu.fleetmanagement.repository.AppUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    public CustomUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByUtilizator(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilizatorul nu exista"));

        return new User(
                user.getUtilizator(),
                user.getParola(),
                List.of(new SimpleGrantedAuthority(user.getRol()))
        );
    }
}