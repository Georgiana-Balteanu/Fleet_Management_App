package com.exemplu.fleetmanagement;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String rawPassword = "admin_password";
        String hash = encoder.encode(rawPassword);

        System.out.println("HASH:");
        System.out.println(hash);

        System.out.println("MATCHES?");
        System.out.println(encoder.matches(rawPassword, hash));
    }
}