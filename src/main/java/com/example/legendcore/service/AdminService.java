package com.example.legendcore.service;

import com.example.legendcore.entity.Admin;
import com.example.legendcore.exception.RestException;
import com.example.legendcore.payload.ApiResponse;
import com.example.legendcore.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public ApiResponse<List<Admin>> getAll() {
        return ApiResponse.successResponse(adminRepository.findAll());
    }

    public ApiResponse<Admin> getByUsername(String username) {
        Admin admin = adminRepository.findByUsername(username).orElseThrow(() -> RestException.restThrow("username not found"));
        return ApiResponse.successResponse(admin);
    }

    public ApiResponse<Admin> insert(Admin admin) {
        Admin newAdmin = new Admin();
        newAdmin.setRole("ADMIN");
        newAdmin.setDescription(admin.getDescription());
        newAdmin.setUsername(admin.getUsername());
        newAdmin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.save(newAdmin);
        return ApiResponse.successResponse("created successfully");
    }

    public ApiResponse<?> delete(int id) {
        adminRepository.deleteById(id);
        return ApiResponse.successResponse("deleted");
    }

    public ApiResponse<Admin> update(int id, Admin updatedAdmin) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> RestException.restThrow("admin not found"));
        admin.setRole(updatedAdmin.getRole());
        admin.setPassword(updatedAdmin.getPassword());
        admin.setDescription(updatedAdmin.getDescription());
        admin.setUsername(updatedAdmin.getUsername());
        adminRepository.save(admin);
        return ApiResponse.successResponse("edited successfully");
    }
}
