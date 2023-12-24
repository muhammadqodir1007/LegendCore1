package com.example.legendcore.controller;

import com.example.legendcore.entity.User;
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
    public ApiResponse<List<User>> getAllAdmins() {
        return adminService.getAll();
    }

    @GetMapping("/{username}")
    public ApiResponse<User> getAdminByUsername(@PathVariable String username) {
        return adminService.getByUsername(username);
    }

    @PostMapping
    public ApiResponse<User> addAdmin(@RequestBody User user) {
        return adminService.insert(user);
    }

    @PutMapping("/{id}")
    public ApiResponse<User> updateAdmin(@PathVariable int id, @RequestBody User user) {
        return adminService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteAdmin(@PathVariable int id) {
        return adminService.delete(id);
    }
}
