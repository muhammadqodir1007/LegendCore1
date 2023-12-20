package com.example.legendcore.service;

import com.example.legendcore.entity.ItemType;
import com.example.legendcore.exception.RestException;
import com.example.legendcore.payload.ApiResponse;
import com.example.legendcore.repository.ItemTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemTypeService {

    private final ItemTypeRepository itemTypeRepository;

    public ApiResponse<List<ItemType>> getAll() {
        List<ItemType> allItemTypes = itemTypeRepository.findAll();
        return ApiResponse.successResponse(allItemTypes);
    }

    public ApiResponse<ItemType> getById(int id) {
        ItemType itemType = itemTypeRepository.findById(id)
                .orElseThrow(() -> RestException.restThrow("Item type not found"));
        return ApiResponse.successResponse(itemType);
    }

    public ApiResponse<?> insert(ItemType itemType) {
        ItemType savedItemType = itemTypeRepository.save(itemType);
        return ApiResponse.successResponse(savedItemType, "Inserted successfully");
    }

    public ApiResponse<?> delete(int id) {
        itemTypeRepository.deleteById(id);
        return ApiResponse.successResponse("Deleted successfully");
    }

    public ApiResponse<?> update(int id, ItemType itemType) {
        ItemType existingItemType = itemTypeRepository.findById(id)
                .orElseThrow(() -> RestException.restThrow("Item type not found with this ID"));
        existingItemType.setName(itemType.getName());
        ItemType updatedItemType = itemTypeRepository.save(existingItemType);
        return ApiResponse.successResponse(updatedItemType, "Updated successfully");
    }
}
