package com.example.legendcore.controller;

import com.example.legendcore.entity.Item;
import com.example.legendcore.payload.ApiResponse;
import com.example.legendcore.payload.ItemDto;
import com.example.legendcore.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/item")
public class ItemController {

    private final ItemService itemService;


    @GetMapping
    public ApiResponse<List<Item>> getAllItems() {
        return itemService.getAll();
    }

    @GetMapping("/category/{id}")
    public ApiResponse<List<Item>> getItemsByCategoryId(@PathVariable int id) {
        return itemService.getAllByCategory(id);
    }

    @GetMapping("/{id}")
    public ApiResponse<Item> getItemById(@PathVariable int id) {
        return itemService.getById(id);
    }

    @PostMapping
    public ApiResponse<?> addItem(@RequestBody ItemDto itemDto) {
        return itemService.insert(itemDto);
    }

    @DeleteMapping
    public ApiResponse<?> deleteItemById(@RequestBody ItemDto itemDto) {
        return itemService.delete(itemDto);
    }
}
