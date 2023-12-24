package com.example.legendcore.service;

import com.example.legendcore.entity.Role;
import com.example.legendcore.entity.User;
import com.example.legendcore.exception.RestException;
import com.example.legendcore.payload.ApiResponse;
import com.example.legendcore.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ApiResponse<List<User>> getAll() {
        return ApiResponse.successResponse(userRepository.findAll());
    }

    public ApiResponse<User> getByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> RestException.restThrow("username not found"));
        return ApiResponse.successResponse(user);
    }

    public ApiResponse<User> insert(User user) {
        User newUser = new User();
        newUser.setRole(Role.ADMIN);
        newUser.setDescription(user.getDescription());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(newUser);
        return ApiResponse.successResponse("created successfully");
    }

    public ApiResponse<?> delete(int id) {
        userRepository.deleteById(id);
        return ApiResponse.successResponse("deleted");
    }

    public ApiResponse<User> update(int id, User updatedUser) {
        User user = userRepository.findById(id).orElseThrow(() -> RestException.restThrow("user not found"));
        user.setRole(updatedUser.getRole());
        user.setPassword(updatedUser.getPassword());
        user.setDescription(updatedUser.getDescription());
        user.setUsername(updatedUser.getUsername());
        userRepository.save(user);
        return ApiResponse.successResponse("edited successfully");
    }
}
