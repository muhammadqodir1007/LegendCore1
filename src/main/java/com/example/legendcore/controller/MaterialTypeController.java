package com.example.legendcore.controller;

import com.example.legendcore.entity.MaterialType;
import com.example.legendcore.payload.ApiResponse;
import com.example.legendcore.service.MaterialTypeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/material-type")
@AllArgsConstructor
public class MaterialTypeController {

    private final MaterialTypeService materialTypeService;

    @GetMapping
    public ApiResponse<List<MaterialType>> getAllMaterialTypes() {
        return materialTypeService.getAll();
    }

    @GetMapping("/{id}")
    public ApiResponse<MaterialType> getMaterialTypeById(@PathVariable int id) {
        return materialTypeService.getById(id);
    }

    @PostMapping
    public ApiResponse<?> createMaterialType(@RequestBody MaterialType materialType) {
        return materialTypeService.insert(materialType);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteMaterialType(@PathVariable int id) {
        return materialTypeService.delete(id);
    }

    @PatchMapping("/{id}")
    public ApiResponse<?> updateMaterialType(@PathVariable int id, @RequestBody MaterialType materialType) {
        return materialTypeService.update(id, materialType);
    }
}
