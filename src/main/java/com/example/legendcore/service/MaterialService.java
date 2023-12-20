package com.example.legendcore.service;

import com.example.legendcore.entity.Admin;
import com.example.legendcore.entity.Material;
import com.example.legendcore.entity.MaterialCategory;
import com.example.legendcore.entity.MaterialType;
import com.example.legendcore.exception.RestException;
import com.example.legendcore.payload.ApiResponse;
import com.example.legendcore.payload.MaterialDto;
import com.example.legendcore.repository.AdminRepository;
import com.example.legendcore.repository.MaterialCategoryRepository;
import com.example.legendcore.repository.MaterialRepository;
import com.example.legendcore.repository.MaterialTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialTypeRepository materialTypeRepository;
    MaterialCategoryRepository materialCategoryRepository;
    private final MaterialTransactionService materialTransactionService;
    private final AdminRepository adminRepository;

    public ApiResponse<List<Material>> getAll() {
        List<Material> allMaterials = materialRepository.findAll();
        return ApiResponse.successResponse(allMaterials);
    }

    public ApiResponse<Material> getById(int id) {
        Material material = materialRepository.findById(id).orElseThrow(() -> RestException.restThrow("Material not found"));
        return ApiResponse.successResponse(material);
    }

    public ApiResponse<?> insert(MaterialDto materialDto) {
        MaterialCategory category = materialCategoryRepository.findById(materialDto.getCategoryId()).orElseThrow(() -> RestException.restThrow("Category not found"));
        Admin admin = adminRepository.findById(materialDto.getAdminId()).orElseThrow(() -> RestException.restThrow("Admin not found"));
        MaterialType itemType = materialTypeRepository.findById(materialDto.getMaterialTypeId()).orElseThrow(() -> RestException.restThrow("Item Type not found"));


        boolean isExist = materialRepository.existsByMaterialType(itemType);
        Material item;
        if (isExist) {
            item = materialRepository.findByMaterialType(itemType).orElseThrow(() -> RestException.restThrow("item not found"));
            item.setQuantity(item.getQuantity() + materialDto.getQuantity());
//            itemRepository.save(item);
            item.setUpdatedAt(LocalDateTime.now());
        } else {
            item = new Material();
            item.setMaterialType(itemType);
            item.setMaterialCategory(category);
            item.setDescription(materialDto.getDescription());
            item.setQuantity(materialDto.getQuantity());
            item.setCreatedAt(LocalDateTime.now());
        }
        item.setAdmin(admin);


        materialRepository.save(item);

        materialTransactionService.add(materialDto, "added");
        return ApiResponse.successResponse(item, "Material successfully saved");
    }


    public ApiResponse<List<Material>> getAllByCategoryId(int id) {
        List<Material> all = materialRepository.findAllByMaterialCategoryId(id);
        return ApiResponse.successResponse(all);


    }

    public ApiResponse<?> delete(MaterialDto materialDto) {

        MaterialType itemType1 = materialTypeRepository.findById(materialDto.getMaterialTypeId()).orElseThrow(() -> RestException.restThrow("item type not found"));
        Admin admin = adminRepository.findById(materialDto.getAdminId()).orElseThrow(() -> RestException.restThrow("admin not found"));
        boolean exists = materialRepository.existsByMaterialType(itemType1);
        Material save;
        if (exists) {
            Material item = materialRepository.findByMaterialType(itemType1).orElseThrow(() -> RestException.restThrow("not found"));
            if (item.getQuantity() < materialDto.getQuantity())
                throw RestException.restThrow("there is not enough product");
            item.setQuantity(item.getQuantity() - materialDto.getQuantity());
            item.setAdmin(admin);
            save = materialRepository.save(item);

        } else {
            throw RestException.restThrow("item not found");
        }
        materialTransactionService.delete(materialDto, "olindi");


        return ApiResponse.successResponse(save);
    }
}

