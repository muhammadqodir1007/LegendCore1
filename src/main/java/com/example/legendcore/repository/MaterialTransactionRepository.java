package com.example.legendcore.repository;

import com.example.legendcore.entity.MaterialTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialTransactionRepository extends JpaRepository<MaterialTransaction, Integer> {
    List<MaterialTransaction> findAllByAdminId(int id);

}
