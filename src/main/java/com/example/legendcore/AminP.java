package com.example.legendcore;

import com.example.legendcore.entity.Role;
import com.example.legendcore.entity.User;
import com.example.legendcore.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AminP implements ApplicationRunner {

    PasswordEncoder passwordEncoder;
    UserRepository adminRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        User admin = new User();
        admin.setUsername("Alex1");
        admin.setPassword(passwordEncoder.encode("password"));
        admin.setRole(Role.ADMIN);

        adminRepository.save(admin);
        User director = new User();
        director.setUsername("Alex2");
        director.setPassword(passwordEncoder.encode("password"));
        director.setRole(Role.DIRECTOR);

        adminRepository.save(director);

    }
}
