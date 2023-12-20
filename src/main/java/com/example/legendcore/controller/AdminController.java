package com.example.legendcore.controller;

import com.example.legendcore.entity.Admin;
import com.example.legendcore.payload.ApiResponse;
import com.example.legendcore.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ApiResponse<List<Admin>> getAllAdmins() {
        return adminService.getAll();
    }

    @GetMapping("/{username}")
    public ApiResponse<Admin> getAdminByUsername(@PathVariable String username) {
        return adminService.getByUsername(username);
    }

    @PostMapping
    public ApiResponse<Admin> addAdmin(@RequestBody Admin admin) {
        return adminService.insert(admin);
    }

    @PutMapping("/{id}")
    public ApiResponse<Admin> updateAdmin(@PathVariable int id, @RequestBody Admin admin) {
        return adminService.update(id, admin);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteAdmin(@PathVariable int id) {
        return adminService.delete(id);
    }
}
