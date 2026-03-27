package com.exemplu.fleetmanagement.repository;

import com.exemplu.fleetmanagement.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUtilizator(String utilizator);
}