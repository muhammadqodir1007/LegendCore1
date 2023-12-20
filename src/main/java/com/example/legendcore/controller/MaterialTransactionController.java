package com.example.legendcore.controller;
import com.example.legendcore.entity.MaterialTransaction;
import com.example.legendcore.payload.ApiResponse;
import com.example.legendcore.service.MaterialTransactionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/material-transactions")
public class MaterialTransactionController {

    private final MaterialTransactionService materialTransactionService;

    public MaterialTransactionController(MaterialTransactionService materialTransactionService) {
        this.materialTransactionService = materialTransactionService;
    }

    @GetMapping
    public ApiResponse<List<MaterialTransaction>> getAllMaterialTransactions() {
        return materialTransactionService.getAll();
    }

    @GetMapping("/{id}")
    public ApiResponse<MaterialTransaction> getMaterialTransactionById(@PathVariable int id) {
        return materialTransactionService.getById(id);
    }

    @GetMapping("/admin/{id}")
    public ApiResponse<List<MaterialTransaction>> getMaterialTransactionsByAdminId(@PathVariable int id) {
        return materialTransactionService.getByAdminId(id);
    }

}
