package com.example.legendcore.service;

import com.example.legendcore.entity.MaterialCategory;
import com.example.legendcore.exception.RestException;
import com.example.legendcore.payload.ApiResponse;
import com.example.legendcore.repository.MaterialCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MaterialCategoryService {

    private final MaterialCategoryRepository materialCategoryRepository;

    public ApiResponse<List<MaterialCategory>> getAll() {
        List<MaterialCategory> allCategories = materialCategoryRepository.findAll();
        return ApiResponse.successResponse(allCategories);
    }

    public ApiResponse<?> insert(MaterialCategory materialCategory) {
        MaterialCategory savedCategory = materialCategoryRepository.save(materialCategory);
        return ApiResponse.successResponse(savedCategory, "Inserted successfully");
    }

    public ApiResponse<MaterialCategory> getById(int id) {
        MaterialCategory category = materialCategoryRepository.findById(id)
                .orElseThrow(() -> RestException.restThrow("Category not found"));
        return ApiResponse.successResponse(category);
    }

    public ApiResponse<?> delete(int id) {
        materialCategoryRepository.deleteById(id);
        return ApiResponse.successResponse("Deleted successfully");
    }

    public ApiResponse<?> update(int id, MaterialCategory materialCategory) {
        MaterialCategory existingCategory = materialCategoryRepository.findById(id)
                .orElseThrow(() -> RestException.restThrow("Category not found"));
        existingCategory.setName(materialCategory.getName());
        MaterialCategory updatedCategory = materialCategoryRepository.save(existingCategory);
        return ApiResponse.successResponse(updatedCategory, "Updated successfully");
    }
}
