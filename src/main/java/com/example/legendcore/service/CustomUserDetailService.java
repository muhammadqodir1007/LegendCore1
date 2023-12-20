package com.example.legendcore.service;

import com.example.legendcore.entity.Admin;
import com.example.legendcore.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {


    AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Optional<Admin> byUsername = adminRepository.findByUsername(username);
        if (byUsername.isEmpty()) {
            throw new UsernameNotFoundException("admin not found with this username");
        }
        Admin admin = byUsername.get();

        return User.withUsername(admin.getUsername())
                .password(admin.getPassword())
                .roles(admin.getRole())
                .build();


    }
}
